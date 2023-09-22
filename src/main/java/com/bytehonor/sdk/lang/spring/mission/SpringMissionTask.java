package com.bytehonor.sdk.lang.spring.mission;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.thread.LoopIntervalTask;
import com.google.common.base.Objects;

/**
 * 竞争任务
 * 
 * @author lijianqiang
 *
 */
public class SpringMissionTask extends LoopIntervalTask {

    private static final Logger LOG = LoggerFactory.getLogger(SpringMissionThread.class);

    private final String name;

    private final SpringMissionLocker locker;

    private final SpringMissionConfig config;

    private final List<SpringMission> missions;

    private String target;

    public SpringMissionTask(String name, SpringMissionLocker locker, SpringMissionConfig config,
            List<SpringMission> missions) {
        this.target = "";
        this.name = name;
        this.locker = locker;
        this.config = config;
        this.missions = missions;
    }

    @Override
    public long delays() {
        return config.getTaskDelayMillis();
    }

    @Override
    public long intervals() {
        return config.getTaskIntervalMillis();
    }

    @Override
    public void runThenSleep() {
        try {
            doCompete();
            doKeep();
            doCheck();
        } catch (Exception e) {
            LOG.error("doRun error", e);
        }
    }

    private void doCompete() {
        if (SpringString.isEmpty(target) == false) {
            return;
        }

        if (CollectionUtils.isEmpty(missions)) {
            LOG.info("missions empty");
            return;
        }

        for (SpringMission mission : missions) {
            if (locker.lock(mission.target(), name, config.getTaskLockMillis()) == false) {
                continue;
            }

            target = mission.target();
            mission.start();
            LOG.info("doCompete target:{}, name:{}", target, name);
            break;
        }
    }

    private void doKeep() {
        if (SpringString.isEmpty(target)) {
            LOG.info("doKeep target null, name:{}", name);
            return;
        }

        String val = locker.get(target);
        boolean success = Objects.equal(val, name);
        LOG.info("doKeep success:{}, target:{}, name:{}", success, target, name);
        if (success) {
            locker.expireAt(target, System.currentTimeMillis() + config.getTaskLockMillis());
        }
    }

    private void doCheck() {
        if (CollectionUtils.isEmpty(missions)) {
            return;
        }

        for (SpringMission mission : missions) {
            if (SpringString.isEmpty(locker.get(mission.target()))) {
                LOG.warn("doCheck target:{} no runner", mission.target());
            }
        }
    }
}
