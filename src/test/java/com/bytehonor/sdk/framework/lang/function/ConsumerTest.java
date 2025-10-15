package com.bytehonor.sdk.framework.lang.function;

import java.util.function.BiConsumer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.framework.lang.function.consumer.ConsumeString;
import com.bytehonor.sdk.framework.lang.function.setter.SetString;

public class ConsumerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerTest.class);

    @Test
    public void test1() {
        // 对象set方法
        SetterModel model = new SetterModel();
        model.setName("test1");
        LOG.info("name:{}", model.getName());

        SetString<SetterModel> setter = SetterModel::setName;
        setter.accept(model, "test1test1");
        LOG.info("name:{}", model.getName());
    }

    @Test
    public void test2() {
        // 类的对象方法
        SetterModel model = new SetterModel();
        model.setName("test2");
        LOG.info("name:{}", model.getName());

        model.print("00000");

        BiConsumer<SetterModel, String> setter1 = SetterModel::print; // setName 和 print 一样单参
        setter1.accept(model, "first");

        SetString<SetterModel> setter2 = SetterModel::print; // setName 和 print 一样单参
        setter2.accept(model, "second");

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
