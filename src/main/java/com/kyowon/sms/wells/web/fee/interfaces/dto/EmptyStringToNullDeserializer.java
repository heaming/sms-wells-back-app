package com.kyowon.sms.wells.web.fee.interfaces.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;


public class EmptyStringToNullDeserializer extends StdDeserializer<String> {

    public EmptyStringToNullDeserializer() {
        this(null);
    }

    public EmptyStringToNullDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String value = jsonParser.getText();
        if (value != null && value.isEmpty()) {
            return null;
        }
        return value;
    }
}
