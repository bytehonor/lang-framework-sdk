package com.bytehonor.sdk.framework.lang.function;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectGetterTest {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectGetterTest.class);

    @Test
    public void test() {
        long now = System.currentTimeMillis();
        Student student = new Student();
        student.setAge(1);
        student.setNickname("boy");
        student.setId(123L);
        student.setCreateAt(now);
        student.setUpdateAt(now);

        ObjectGetter<String> getter = student::getNickname;
        String res = getter.get();
        LOG.info("getter:{} = {}", getter.toString(), res);

        assertTrue("test", student.getNickname().equals(res));
    }

}
