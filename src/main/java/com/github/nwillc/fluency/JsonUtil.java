package com.github.nwillc.fluency;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.util.Map;

public final class JsonUtil {
    private static final ThreadLocal<ObjectMapper> JSON_MAPPER = ThreadLocal.withInitial(() ->
            new ObjectMapper().registerModule(new Jdk8Module())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));

    private JsonUtil() {
    }

    public static Map toMap(String json) {
        try {
            return JSON_MAPPER.get().readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("JSON parsing", e);
        }
    }
}
