package com.scccy.downloadvideo.common.core.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import feign.codec.EncodeException;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractDecoder;
import org.springframework.core.codec.AbstractEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.lang.reflect.Type;

public class FastjsonConfig {
    public static class CustomDecoder extends AbstractDecoder<Object> implements feign.codec.Decoder {
        public CustomDecoder() {
            super(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML);
        }

        @Override
        public Object decode(feign.Response response, Type type) throws IOException {
            String bodyStr = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return JSON.parseObject(bodyStr, type);
        }

        @Override
        public Flux<Object> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType, 
                                 MimeType mimeType, Map<String, Object> hints) {
            return Flux.from(inputStream)
                    .map(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        return new String(bytes, StandardCharsets.UTF_8);
                    })
                    .map(json -> JSON.parseObject(json, elementType.getType()));
        }
    }

    //序列化
    public static class CustomEncoder extends AbstractEncoder<Object> implements feign.codec.Encoder {
        public CustomEncoder() {
            super(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML);
        }

        @Override
        public void encode(Object object, Type bodyType, feign.RequestTemplate template) {
            if (object != null) {
                template.body(JSON.toJSONString(object));
            }
        }

        @Override
        public Flux<DataBuffer> encode(Publisher<?> inputStream, DataBufferFactory bufferFactory,
                                     ResolvableType elementType, MimeType mimeType, 
                                     Map<String, Object> hints) {
            return Flux.from(inputStream)
                    .map(obj -> {
                        byte[] bytes = JSON.toJSONBytes(obj);
                        return bufferFactory.wrap(bytes);
                    });
        }
    }
}
