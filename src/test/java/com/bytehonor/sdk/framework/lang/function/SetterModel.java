package com.bytehonor.sdk.framework.lang.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetterModel {

    private static final Logger LOG = LoggerFactory.getLogger(SetterModel.class);

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print(String val) {
        LOG.info("name:{} print val:{}", name, val);
    }

    public static void hello(SetterModel model, String val) {
        LOG.info("name:{} hello val:{}", model.getName(), val);
    }
}
