package com.bytehonor.sdk.framework.lang.util;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.exception.SpringLangException;
import com.bytehonor.sdk.framework.lang.string.StringKit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 基于单例 {@link ObjectMapper} 的 JSON 读写工具（反序列化默认忽略未知字段）。
 *
 * @author lijianqiang
 */
public class JacksonKit {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonKit.class);

    private static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();

    static {
        JACKSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将 JSON 字符串反序列化为指定 {@code valueType}。
     */
    public static <T> T fromJson(String json, Class<T> valueType) {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueType, "valueType");
        try {
            return JACKSON_MAPPER.readValue(json, valueType);
        } catch (Exception e) {
            LOG.error("error json:{}", cut(json));
            throw new SpringLangException("readValue valueType error", e);
        }
    }

    private static String cut(String text) {
        if (StringKit.isEmpty(text)) {
            return "";
        }
        return text.length() < 512 ? text : text.substring(0, 510);
    }

    /**
     * 将 JSON 字符串反序列化为泛型类型（如 {@code List<String>}、{@code Map<...>}）。
     */
    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        Objects.requireNonNull(json, "json");
        Objects.requireNonNull(valueTypeRef, "valueTypeRef");
        try {
            return JACKSON_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            LOG.error("error json:{}", cut(json));
            throw new SpringLangException("readValue valueTypeRef error", e);
        }
    }

    /**
     * 将对象序列化为 JSON 字符串。
     */
    public static String toJson(Object value) {
        Objects.requireNonNull(value, "value");
        try {
            return JACKSON_MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            throw new SpringLangException("writeValueAsString error", e);
        }
    }

    /**
     * 将 JSON 字符串解析为 {@link JsonNode} 树。
     */
    public static JsonNode readTree(String json) {
        Objects.requireNonNull(json, "json");
        try {
            return JACKSON_MAPPER.readTree(json);
        } catch (Exception e) {
            LOG.error("error json:{}", cut(json));
            throw new SpringLangException("readTree error", e);
        }
    }

    public static String get(JsonNode jn, String key) {
        if (jn == null) {
            LOG.warn("jn null, get key:{}", key);
            return null;
        }
        return jn.has(key) ? jn.get(key).asText() : null;
    }
    
    public static String gets(JsonNode jn, String key, String defValue) {
        if (jn == null) {
            LOG.warn("jn null, get key:{}", key);
            return null;
        }
        return jn.has(key) ? jn.get(key).asText() : defValue;
    }

    public static String get(JsonNode jn, int index) {
        if (jn == null) {
            LOG.warn("jn null, get index:{}", index);
            return null;
        }
        return jn.has(index) ? jn.get(index).asText() : null;
    }
    
    public static String gets(JsonNode jn, int index, String defValue) {
        if (jn == null) {
            LOG.warn("jn null, get index:{}", index);
            return null;
        }
        return jn.has(index) ? jn.get(index).asText() : defValue;
    }

    public static JsonNode require(JsonNode jn, String field) {
        Objects.requireNonNull(jn, "jn");
        Objects.requireNonNull(field, "field");
        JsonNode node = jn.get(field);
        if (node == null) {
            throw new SpringLangException("JsonNode null, field:" + field);
        }
        return node;
    }
}
