package com.bytehonor.sdk.lang.bytehonor.util;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.bytehonor.exception.BytehonorLangException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);

    private static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();

    static {
        JACKSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueType, "valueType");
        try {
            return JACKSON_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            throw new BytehonorLangException("readValue valueType error", e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueTypeRef, "valueTypeRef");
        try {
            return JACKSON_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new BytehonorLangException("readValue valueTypeRef error", e);
        }
    }

    public static String toJson(Object value) {
        Objects.requireNonNull(value, "value");
        try {
            return JACKSON_MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            throw new BytehonorLangException("writeValueAsString error", e);
        }
    }

    public static JsonNode readTree(String json) {
        Objects.requireNonNull(json, "json");
        try {
            return JACKSON_MAPPER.readTree(json);
        } catch (Exception e) {
            throw new BytehonorLangException("readTree error", e);
        }
    }

    public static String get(JsonNode jn, String key) {
        if (jn == null) {
            LOG.warn("jn null, get key:{}", key);
            return null;
        }
        return jn.has(key) ? jn.get(key).asText() : null;
    }

    public static String get(JsonNode jn, int index) {
        if (jn == null) {
            LOG.warn("jn null, get index:{}", index);
            return null;
        }
        return jn.has(index) ? jn.get(index).asText() : null;
    }

    public static JsonNode nodeRequired(JsonNode jn, String field) {
        Objects.requireNonNull(jn, "jn");
        Objects.requireNonNull(field, "field");
        JsonNode node = jn.get(field);
        if (node == null) {
            throw new BytehonorLangException("JsonNode null, field:" + field);
        }
        return node;
    }
}
