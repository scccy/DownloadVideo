package com.scccy.downloadvideo.platform.douyin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;

import com.scccy.downloadvideo.platform.douyin.config.NodeJsProperties;

@Slf4j
@Component
public class WebcastSignatureUtil {

    private final String nodePath;
    private final String scriptPath;

    public WebcastSignatureUtil(NodeJsProperties nodeJsProperties) {
        try {
            // 复制 JS 文件到临时目录
            ClassPathResource signatureResource = new ClassPathResource("algorithm/webcast_signature.js");
            ClassPathResource nodeResource = new ClassPathResource("algorithm/webcast_signature_node.js");

            Path tempDir = Files.createTempDirectory("douyin-signature");
            Path signatureJs = tempDir.resolve("webcast_signature.js");
            Path nodeJs = tempDir.resolve("webcast_signature_node.js");

            try (InputStream signatureIs = signatureResource.getInputStream();
                 InputStream nodeIs = nodeResource.getInputStream()) {
                Files.copy(signatureIs, signatureJs, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(nodeIs, nodeJs, StandardCopyOption.REPLACE_EXISTING);

                // 添加执行权限
                File nodeJsFile = nodeJs.toFile();
                if (!nodeJsFile.setExecutable(true)) {
                    log.warn("无法为脚本文件添加执行权限: {}", nodeJs);
                }
            }

            this.nodePath = nodeJsProperties.getPath();
            this.scriptPath = nodeJs.toString();

            // 验证 Node.js 是否可用
            validateNodeJs();

            // 添加关闭钩子清理临时文件
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Files.deleteIfExists(signatureJs);
                    Files.deleteIfExists(nodeJs);
                    Files.deleteIfExists(tempDir);
                } catch (Exception e) {
                    log.error("清理临时文件失败", e);
                }
            }));
        } catch (Exception e) {
            log.error("初始化签名工具失败: {}", e.getMessage());
            throw new RuntimeException("初始化签名工具失败", e);
        }
    }

    private void validateNodeJs() {
        try {
            File nodeFile = new File(nodePath);
            if (!nodeFile.exists()) {
                throw new RuntimeException("Node.js 可执行文件不存在: " + nodePath);
            }
            if (!nodeFile.canExecute()) {
                log.warn("Node.js 文件没有执行权限，尝试添加执行权限: {}", nodePath);
                if (!nodeFile.setExecutable(true)) {
                    throw new RuntimeException("无法为 Node.js 添加执行权限: " + nodePath);
                }
            }

            ProcessBuilder pb = new ProcessBuilder(nodePath, "--version");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            String output = StreamUtils.copyToString(process.getInputStream(), StandardCharsets.UTF_8);
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("Node.js 不可用: " + output);
            }

            log.info("Node.js 版本: {}", output.trim());
        } catch (Exception e) {
            log.error("验证 Node.js 失败: {}", e.getMessage());
            throw new RuntimeException("验证 Node.js 失败", e);
        }
    }

    public String getSignature(String roomId, String userUniqueId) {
        try {
            // 构造待签名字符串
            String rawString = String.format(
                "live_id=1,aid=6383,version_code=180800,webcast_sdk_version=1.0.14-beta.0,room_id=%s,sub_room_id=,sub_channel_id=,did_rule=3,user_unique_id=%s,device_platform=web,device_type=,ac=,identity=audience",
                roomId, userUniqueId
            );

            // 计算 MD5
            String xMsStub = calculateMD5(rawString);

            // 调用 Node.js 脚本
            ProcessBuilder pb = new ProcessBuilder(nodePath, scriptPath, xMsStub, "get_signature");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // 读取输出
            String output = StreamUtils.copyToString(process.getInputStream(), StandardCharsets.UTF_8);
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("Node.js 脚本执行失败: " + output);
            }

            return output.trim();
        } catch (Exception e) {
            log.error("生成签名失败", e);
            throw new RuntimeException("生成签名失败", e);
        }
    }

    private String calculateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            log.error("计算MD5失败", e);
            throw new RuntimeException("计算MD5失败", e);
        }
    }
}