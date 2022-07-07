package com.awesome.wow.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class JsonUtil {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getMapper() {
        if (objectMapper == null) {
            Class<JsonUtil> var0 = JsonUtil.class;
            synchronized(JsonUtil.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                }
            }
        }

        return objectMapper;
    }

    public static <T> T toObject(String jsonStr, TypeReference<T> valueType) {
        T result = null;

        try {
            result = getMapper().readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
