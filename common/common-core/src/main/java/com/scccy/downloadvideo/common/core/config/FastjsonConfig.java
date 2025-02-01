package com.scccy.downloadvideo.common.core.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.AbstractDecoder;
import org.springframework.core.codec.AbstractEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FastjsonConfig {
    public static class CustomDecoder extends AbstractDecoder<Object> {
        public CustomDecoder() {
            super(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML);
        }

        @Override
        public Flux<Object> decode(Publisher<DataBuffer> inputStream, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
            return Flux.from(inputStream)
                    .map(dataBuffer -> dataBuffer.toString(StandardCharsets.UTF_8))
                    .map(json -> JSON.parseObject(
                            json, elementType.getType(),
                            JSONReader.Feature.FieldBased,
                            JSONReader.Feature.SupportArrayToBean
                    ));
        }
    }


    //序列化
    public static class CustomEncoder extends AbstractEncoder<Object> {
        public CustomEncoder() {
            super(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML);
        }

        @Override
        public Flux<DataBuffer> encode(Publisher<?> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType, MimeType mimeType, Map<String, Object> hints) {
            return Flux.from(inputStream)
                    .map(obj -> {
                        return JSON.toJSONBytes(obj,
                                JSONWriter.Feature.WriteMapNullValue,
                                JSONWriter.Feature.WriteNullListAsEmpty,
                                JSONWriter.Feature.WriteNullStringAsEmpty);
                    })
                    .map(bufferFactory::wrap);
        }
    }
}
