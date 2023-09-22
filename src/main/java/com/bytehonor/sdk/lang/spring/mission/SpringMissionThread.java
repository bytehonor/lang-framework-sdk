package com.bytehonor.sdk.lang.spring.mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.exception.SpringLangException;
import com.bytehonor.sdk.lang.spring.thread.LoopIntervalThread;

/**
 * 竞争任务线程
 * 
 * @author lijianqiang
 *
 */
public class SpringMissionThread {

    private final LoopIntervalThread thread;

    private SpringMissionThread(LoopIntervalThread thread) {
        this.thread = thread;
    }

    public void start() {
        thread.start();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private SpringMissionLocker locker;
        private SpringMissionConfig config;
        private List<SpringMission> missions;

        private Builder() {
            this.config = new SpringMissionConfig();
            this.missions = new ArrayList<SpringMission>();
        }

        public Builder mission(SpringMission mission) {
            Objects.requireNonNull(mission, "mission");

            this.missions.add(mission);
            return this;
        }

        public Builder name(String name) {
            Objects.requireNonNull(name, "name");

            this.name = name;
            return this;
        }

        public Builder locker(SpringMissionLocker locker) {
            Objects.requireNonNull(locker, "locker");

            this.locker = locker;
            return this;
        }

        public Builder config(SpringMissionConfig config) {
            Objects.requireNonNull(config, "config");

            this.config = config;
            return this;
        }

        public SpringMissionThread build() {
            Objects.requireNonNull(name, "name");
            Objects.requireNonNull(locker, "locker");
            Objects.requireNonNull(config, "config");

            if (CollectionUtils.isEmpty(missions)) {
                throw new SpringLangException("missions empty");
            }

            return new SpringMissionThread(new LoopIntervalThread(SpringMissionThread.class.getSimpleName(),
                    new SpringMissionTask(name, locker, config, missions)));
        }
    }
}
