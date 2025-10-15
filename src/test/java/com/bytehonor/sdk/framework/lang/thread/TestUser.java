package com.bytehonor.sdk.framework.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUser {

    private static final Logger LOG = LoggerFactory.getLogger(TestUser.class);

    private Integer id;

    public static TestUser of(int id) {
        TestUser model = new TestUser();
        model.setId(id);
        return model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static void consume(TestUser user) {
        LOG.info("id:{}", user.getId());
        Sleep.millis(1200L);
    }
}
