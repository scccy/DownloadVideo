package com.scccy.downloadvideo.platform.douyin.model;

import com.scccy.downloadvideo.platform.douyin.utils.WebcastSignatureUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

/**
 * 抖音直播间签名
 * 用于生成和验证直播间的签名
 */
@Data
@Accessors(chain = true)
@Component
public class DouyinWebcastSignature {

    @Autowired
    private WebcastSignatureUtil signatureUtil;

    /**
     * 直播间ID
     */
    private String roomId;

    /**
     * 用户唯一标识
     */
    private String userUniqueId;

    /**
     * X-MS-STUB 参数
     */
    private String xMsStub;

    /**
     * X-Bogus 签名
     */
    private String xBogus;

    public DouyinWebcastSignature() {
    }

    /**
     * 构造签名对象
     * @param roomId 直播间ID
     * @param userUniqueId 用户唯一标识
     */
    public DouyinWebcastSignature(String roomId, String userUniqueId) {
        this.roomId = roomId;
        this.userUniqueId = userUniqueId;
    }

    /**
     * 获取待签名的原始字符串
     */
    public String getRawString() {
        return String.format(
                "live_id=1,aid=6383,version_code=180800,webcast_sdk_version=1.0.14-beta.0,room_id=%s,sub_room_id=,sub_channel_id=,did_rule=3,user_unique_id=%s,device_platform=web,device_type=,ac=,identity=audience",
                roomId, userUniqueId
        );
    }

    /**
     * 生成资源路径所需的签名
     * @return this 返回当前对象，支持链式调用
     */
    public DouyinWebcastSignature getResourcePath() {
        // 1. 生成原始字符串
        String rawString = getRawString();

        // 2. 计算 X-MS-STUB
        this.xMsStub = calculateMD5(rawString);

        // 3. 使用 Node.js 生成 X-Bogus
        this.xBogus = signatureUtil.getSignature(roomId, userUniqueId);

        return this;
    }

    /**
     * 计算字符串的 MD5 值
     */
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
            throw new RuntimeException("计算MD5失败", e);
        }
    }
}