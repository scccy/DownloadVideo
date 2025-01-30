package com.scccy.downloadvideo.common.core.config;

//import com.scccy.downloadvideo.common.core.exception.ServiceException;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String responseBody = response.body() != null ?
                new String(response.body().asInputStream().readAllBytes()) : "";

            log.error("Feign call failed: method={}, status={}, body={}",
                     methodKey, response.status(), responseBody);

            if (response.status() >= 400 && response.status() <= 499) {
                return new ServiceException("400", "请求参数错误");
            } else if (response.status() >= 500) {
                return new ServiceException("500", "服务器内部错误");
            }

            return new ServiceException("500", "未知错误");
        } catch (IOException e) {
            return new ServiceException("500", "解析响应失败");
        }
    }
} 