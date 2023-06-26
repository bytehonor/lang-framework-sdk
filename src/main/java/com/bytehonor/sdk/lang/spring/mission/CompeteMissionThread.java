package com.bytehonor.sdk.lang.spring.mission;

import java.util.Objects;

import com.bytehonor.sdk.lang.spring.thread.LoopIntervalThread;

/**
 * 竞争任务线程
 * 
 * @author lijianqiang
 *
 */
public class CompeteMissionThread {

    private final LoopIntervalThread thread;

    private CompeteMissionThread(LoopIntervalThread thread) {
        this.thread = thread;
    }

    public void start() {
        thread.start();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CompeteMissionGroup group;
        private String name;
        private CompeteMissionLocker locker;
        private CompleteMissionConfig config;

        private Builder() {
            this.config = new CompleteMissionConfig();
        }

        public Builder group(CompeteMissionGroup group) {
            this.group = group;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder locker(CompeteMissionLocker locker) {
            this.locker = locker;
            return this;
        }

        public Builder config(CompleteMissionConfig config) {
            this.config = config;
            return this;
        }

        public CompeteMissionThread build() {
            Objects.requireNonNull(group, "group");
            Objects.requireNonNull(name, "name");
            Objects.requireNonNull(locker, "locker");

            return new CompeteMissionThread(new LoopIntervalThread(CompeteMissionThread.class.getSimpleName(),
                    new CompeteMissionTask(group, name, locker, config)));
        }
    }
}
