package com.bytehonor.sdk.lang.spring.function;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.function.getter.GetString;

public class GettersTest {

    private static final Logger LOG = LoggerFactory.getLogger(GettersTest.class);

    @Test
    public void test() {
        long now = System.currentTimeMillis();
        Student student = new Student();
        student.setAge(1);
        student.setNickname("test2");
        student.setId(123L);
        student.setCreateAt(now);
        student.setUpdateAt(now);

        GetString<Student> getter = Student::getNickname;
        String nickname = Getters.get(getter, student);

        LOG.info("test {}", Getters.get(Student::getId, student));
        LOG.info("test {}", Getters.get(Student::getAge, student));
        LOG.info("test {}", Getters.get(getter, student));

        assertTrue("test", student.getNickname().equals(nickname));
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

        String nickname = Getters.get(student::getNickname);

        LOG.info("nickname:{}", nickname);
        LOG.info("id:{}", Getters.get(student::getId));
        LOG.info("age:{}", Getters.get(student::getAge));
        assertTrue("test2", student.getNickname().equals(nickname));
    }
}
