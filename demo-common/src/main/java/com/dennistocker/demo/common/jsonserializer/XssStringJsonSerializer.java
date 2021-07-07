package com.dennistocker.demo.common.jsonserializer;

import cn.hutool.core.util.EscapeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @date 2021/6/30 3:31 下午
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (s != null) {
            jsonGenerator.writeString(EscapeUtil.escape(s));
        }
    }
}
