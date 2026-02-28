package com.bytehonor.sdk.framework.lang.function;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.core.field.SerializedLambdaUtil;
import com.bytehonor.sdk.framework.lang.function.setter.SetString;

public class ClassSetterTest {

    private static final Logger LOG = LoggerFactory.getLogger(ClassSetterTest.class);

    @Test
    public void test() {
        long now = System.currentTimeMillis();
        Student student = new Student();
        student.setAge(1);
        student.setNickname("boy");
        student.setId(123L);
        student.setCreateAt(now);
        student.setUpdateAt(now);

        int age = 11;
        ObjIntConsumer<Student> setter = Student::setAge;
        setter.accept(student, age);

        Consumer<Long> cl = student::setId;
        cl.accept(0L);
        LOG.info("id:{}", student.getId());

        List<Consumer<Long>> consumers = new ArrayList<Consumer<Long>>();
        consumers.add(student::setId);

        ClassSetter<Student, Long> setter2 = Student::setCreateAt;
        setter2.accept(student, 555L);
        LOG.info("{}:{}", SerializedLambdaUtil.getFieldName(setter2), student.getCreateAt());

        SetString<Student> setter3 = Student::setNickname;
        setter3.accept(student, "testsetNickname");
        LOG.info("{}:{}", SerializedLambdaUtil.getFieldName(setter3), student.getNickname());

        List<ClassSetter<Student, ?>> setters = new ArrayList<ClassSetter<Student, ?>>();
        setters.add(setter2);
        setters.add(setter3);
        setters.add((ClassSetter<Student, Integer>) Student::setAge);
        setters.add((ClassSetter<Student, String>) Student::setNickname);
        for (ClassSetter<Student, ?> st : setters) {
            LOG.info("name:{}", SerializedLambdaUtil.getFieldName(st));
        }

        LOG.info("test {}, {}", student.getAge(), student.getCreateAt());

        assertTrue("test", age == student.getAge());
    }

    @Test
    public void test2() {
        long now = System.currentTimeMillis();
        Student student = new Student();
        student.setAge(1);
        student.setNickname("boy");
        student.setId(123L);
        student.setCreateAt(now);
        student.setUpdateAt(now);

        int age = 12;
        Setters.set(student::setAge, age);
        Setters.set(student::setNickname, "boy2");
        Setters.set(student::setId, 0L);
        Setters.set(student::setCreateAt, 1111L);
        Setters.set(student::setUpdateAt, 1111L);

        LOG.info("test2 {}, {}", student.getAge(), student.getNickname());

        assertTrue("test2", age == student.getAge());
    }

    @Test
    public void test3() {
        Student student = new Student();
        int age = 13;
        Setters.set(Student::setAge, student, age);
        Setters.set(Student::setNickname, student, "boy3");
        Setters.set(Student::setId, student, 0L);
        Setters.set(Student::setCreateAt, student, 1111L);
        Setters.set(Student::setUpdateAt, student, 1111L);

        LOG.info("test3 {}, {}", student.getAge(), student.getNickname());

        assertTrue("test3", age == student.getAge());
    }

    @Test
    public void test4() {
        BiConsumer<String, String> consumer = Student::testTwo;

        consumer.accept("v1", "v2");

        SetString<String> consumer2 = Student::testTwo;
        consumer2.accept("v11", "v22");

        SetString<Student> consumer3 = Student::print;
        Student student = new Student();
        student.setNickname("test4");
        consumer3.accept(student, "xxxx");
    }
}
