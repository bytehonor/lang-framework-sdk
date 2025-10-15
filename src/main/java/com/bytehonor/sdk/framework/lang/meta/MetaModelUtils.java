package com.bytehonor.sdk.framework.lang.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MetaModelUtils {

    private static final Map<String, MetaModel> CACHES = new ConcurrentHashMap<String, MetaModel>();

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

            MetaModelField modelField = new MetaModelField();
            modelField.setCamel(camel);
            modelField.setUnderline(MetaGetter.underline(camel));
            modelField.setType(field.getType().getName());
            model.add(modelField);
        }

        model.check();

        CACHES.put(name, model);
        return model;
    }
}
