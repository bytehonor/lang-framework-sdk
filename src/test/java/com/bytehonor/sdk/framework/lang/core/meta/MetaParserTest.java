package com.bytehonor.sdk.framework.lang.core.meta;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaParserTest {

    private static final Logger LOG = LoggerFactory.getLogger(MetaParserTest.class);

    @Test
    public void testParse() {
        MetaModel model = MetaParser.parse(Student.class);
        LOG.info("name:{}", model.getName());
        List<MetaField> fields = model.getFields();
        for (MetaField field : fields) {
            LOG.info("camel:{}, underline:{}, type:{}", field.getCamel(), field.getUnderline(), field.getType());
        }
    }

}
