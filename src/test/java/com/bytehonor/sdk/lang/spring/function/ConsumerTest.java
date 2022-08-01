package com.bytehonor.sdk.lang.spring.function;

import java.util.function.BiConsumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.function.consumer.ConsumeString;
import com.bytehonor.sdk.lang.spring.function.setter.SetString;

public class ConsumerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerTest.class);

    @Test
    public void test1() {
        // 对象set方法
        SetterModel model = new SetterModel();
        model.setName("test1");
        LOG.info("name:{}", model.getName());
        SetString<SetterModel> setName = SetterModel::setName;
        setName.accept(model, "test1test1");
        LOG.info("name:{}", model.getName());
    }

    @Test
    public void test2() {
        // 类的对象方法
        SetterModel model = new SetterModel();
        model.setName("test2");
        LOG.info("name:{}", model.getName());
        model.print("first");

        SetString<SetterModel> setName = SetterModel::print;
        setName.accept(model, "second");

        ConsumeString consumer = model::print;
        consumer.accept("third");

        // 类的对象方法静态化，BiConsumer 的首个参数就是该类的实例对象
    }

    @Test
    public void test3() {
        // 类的静态方法
        SetterModel model = new SetterModel();
        model.setName("test3");
        LOG.info("name:{}", model.getName());
        SetterModel.hello(model, "111");

        BiConsumer<SetterModel, String> setName = SetterModel::hello;
        setName.accept(model, "222");
    }
}
