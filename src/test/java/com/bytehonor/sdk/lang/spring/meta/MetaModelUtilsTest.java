package com.bytehonor.sdk.lang.spring.meta;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaModelUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(MetaModelUtilsTest.class);

    @Test
    public void testParse() {
        MetaModel model = MetaModelUtils.parse(Student.class);
        LOG.info("name:{}", model.getName());
        List<MetaModelField> fields = model.getFields();
        for (MetaModelField field : fields) {
            LOG.info("key:{}, column:{}, type:{}", field.getKey(), field.getColumn(), field.getType());
        }
    }

}
