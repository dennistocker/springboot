package com.dennistocker.demo.common.jsonserializer;

import cn.hutool.core.util.EscapeUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @date 2021/6/30 3:28 下午
 */
public class XssStringJsonDeserializer extends JsonDeserializer<String> {

    @Override
    public Class<?> handledType() {
        return String.class;
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        return (value != null) ? EscapeUtil.escape(value) : null;
    }
}
