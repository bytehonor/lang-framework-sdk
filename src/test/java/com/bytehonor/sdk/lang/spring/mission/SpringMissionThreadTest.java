package com.bytehonor.sdk.lang.spring.mission;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.lang.spring.thread.Sleeping;

public class SpringMissionThreadTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringMissionThreadTest.class);

    @Test
    public void test() {
        SpringMissionLocker locker = new SpringMissionLocker() {

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

        SpringMission mission1 = new SpringMission() {

            @Override
            public String target() {
                return "mission2";
            }

            @Override
            public void start() {
                LOG.info("misssion2 run");
            }
        };
        SpringMission mission2 = new SpringMission() {

            @Override
            public String target() {
                return "mission1";
            }

            @Override
            public void start() {
                LOG.info("misssion1 run");
            }
        };

        SpringMissionThread thread = SpringMissionThread.builder().name("test").locker(locker).mission(mission1)
                .mission(mission2).build();
        thread.start();

        Sleeping.sleep(TimeConstants.MINUTE * 10);
    }

}
