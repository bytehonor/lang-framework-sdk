package com.bytehonor.sdk.lang.spring.function;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.function.setter.SetLong;

public class SettersTest {

    private static final Logger LOG = LoggerFactory.getLogger(SettersTest.class);

    @Test
    public void test() {
        Student student = new Student();
        int age = 13;
        SetLong<Student> setter = Student::setId;
        Setters.set(setter, student, 0L);
        Setters.set(Student::setNickname, student, "boy3");
        Setters.set(Student::setAge, student, age);
        Setters.set(Student::setUpdateAt, student, 2222L);
        Setters.set(Student::setCreateAt, student, 1111L);

        LOG.info("test {}, {}, {}, {}, {}", student.getId(), student.getNickname(), student.getAge(),
                student.getUpdateAt(), student.getCreateAt());

        LOG.info("{}", Setters.fieldName(setter));

        assertTrue("test", age == student.getAge());
    }

}
