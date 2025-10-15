package com.bytehonor.sdk.framework.lang.function;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassGetterTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClassGetterTest.class);

    @Test
    public void test() {
        long now = System.currentTimeMillis();
        Student student = new Student();
        student.setAge(1);
        student.setNickname("boy");
        student.setId(123L);
        student.setCreateAt(now);
        student.setUpdateAt(now);

        ClassGetter<Student, ?> getter = Student::getNickname;
        Object res = getter.apply(student);

        LOG.info("getter:{} = {}", getter.toString(), res);

        assertTrue("test", student.getNickname().equals(res));
    }

    @Test
    public void test2() {
        long now = System.currentTimeMillis();
        Student student = new Student();
        student.setAge(1);
        student.setNickname("test2");
        student.setId(123L);
        student.setCreateAt(now);
        student.setUpdateAt(now);

        String nickname = Getters.get(Student::getNickname, student);

        LOG.info("test2 {}", Getters.get(Student::getId, student));
        LOG.info("test2 {}", Getters.get(Student::getAge, student));
        LOG.info("test2 {}", Getters.get(Student::getNickname, student));

        assertTrue("test2", student.getNickname().equals(nickname));
    }
}
