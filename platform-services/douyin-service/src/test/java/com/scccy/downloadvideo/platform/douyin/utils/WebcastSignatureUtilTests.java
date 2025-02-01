package com.scccy.downloadvideo.platform.douyin.utils;



import com.scccy.downloadvideo.platform.douyin.model.DouyinWebcastSignature;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@DisplayName("抖音直播间签名工具测试")
class WebcastSignatureUtilTests {

    @Autowired
    DouyinWebcastSignature douyinWebcastSignature;

    @Test
    @DisplayName("测试生成直播间签名")
    void shouldGenerateSignature() {
        // Given
        String roomId = "7382517534467115826";
        String userUniqueId = "7382524529011246630";
        log.info("测试参数 - roomId: {}, userUniqueId: {}", roomId, userUniqueId);

        try {
            // When
            log.info("开始生成签名...");
            douyinWebcastSignature.setRoomId(roomId)
                          .setUserUniqueId(userUniqueId)
                          .getResourcePath();

            // Then
            String xMsStub = douyinWebcastSignature.getXMsStub();
            String xBogus = douyinWebcastSignature.getXBogus();

            log.info("生成的 X-MS-STUB: {}", xMsStub);
            log.info("生成的 X-Bogus: {}", xBogus);

            // 验证 X-MS-STUB
            assertNotNull(xMsStub, "X-MS-STUB 不应为空");
            assertEquals(32, xMsStub.length(), "X-MS-STUB 长度应为32位");
            assertTrue(xMsStub.matches("[a-f0-9]{32}"), "X-MS-STUB 应为32位十六进制字符串");

            // 验证 X-Bogus
            assertNotNull(xBogus, "X-Bogus 不应为空");
            assertTrue(xBogus.length() > 0, "X-Bogus 长度应大于0");

            log.info("签名验证通过");
        } catch (Exception e) {
            log.error("测试失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    @DisplayName("测试生成原始字符串")
    void shouldGenerateRawString() {
        // Given
        String roomId = "7382517534467115826";
        String userUniqueId = "7382524529011246630";
        douyinWebcastSignature.setRoomId(roomId)
                       .setUserUniqueId(userUniqueId);

        // When
        String rawString = douyinWebcastSignature.getRawString();
        log.info("生成的原始字符串: {}", rawString);

        // Then
        assertNotNull(rawString, "原始字符串不应为空");
        assertTrue(rawString.contains(roomId), "原始字符串应包含房间ID");
        assertTrue(rawString.contains(userUniqueId), "原始字符串应包含用户ID");
        assertTrue(rawString.startsWith("live_id=1"), "原始字符串应以live_id=1开头");
    }
}