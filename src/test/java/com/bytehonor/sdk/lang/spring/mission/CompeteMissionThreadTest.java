package com.bytehonor.sdk.lang.spring.mission;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleeping;

public class CompeteMissionThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(CompeteMissionThreadTest.class);

    @Test
    public void test() {
        CompeteMissionLocker locker = new CompeteMissionLocker() {

            private Map<String, String> map = new HashMap<String, String>();

            @Override
            public boolean lock(String key, String value, long millis) {
                map.put(key, value);
                return true;
            }

            @Override
            public String get(String key) {
                return map.get(key);
            }

            @Override
            public void expireAt(String key, long timestamp) {

            }
        };

        CompeteMissionGroup group = CompeteMissionGroup.create().add(new CompeteMission() {

            @Override
            public String target() {
                return "mission1";
            }

            @Override
            public void run() {
                LOG.info("misssion1 run");
            }
        }).add(new CompeteMission() {

            @Override
            public String target() {
                return "mission2";
            }

            @Override
            public void run() {
                LOG.info("misssion2 run");
            }
        });
        CompeteMissionThread thread = CompeteMissionThread.builder().name("test").locker(locker).group(group).build();
        thread.start();

        Sleeping.sleep(TimeConstants.MINUTE * 10);
    }

}
