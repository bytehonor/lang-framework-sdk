package com.bytehonor.sdk.framework.lang.core.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.bytehonor.sdk.framework.lang.core.field.FieldNameKit;

/**
 * 通过反射解析 POJO 的非静态字段，生成 {@link MetaModel}（按类名全限定名缓存）。
 *
 * @author lijianqiang
 */
public class MetaParser {

    private static final Map<String, MetaModel> CACHES = new ConcurrentHashMap<String, MetaModel>();

    /**
     * 解析 {@code clazz} 的实例字段元信息；已解析过的类从缓存返回。
     *
     * @param clazz 非空类型
     * @return 字段元模型
     */
    public static MetaModel parse(Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz");

        String name = clazz.getName();
        MetaModel model = CACHES.get(name);
        if (model != null) {
            return model;
        }

        model = new MetaModel();
        model.setName(clazz.getName());

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String camel = field.getName();
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            MetaField modelField = new MetaField();
            modelField.setCamel(camel);
            modelField.setUnderline(FieldNameKit.underline(camel));
            modelField.setType(field.getType().getName());
            model.add(modelField);
        }

        model.check();

        CACHES.put(name, model);
        return model;
    }
}
