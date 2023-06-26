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
public class CompeteMissionTask extends LoopIntervalTask {

    private static final Logger LOG = LoggerFactory.getLogger(CompeteMissionThread.class);

    private final CompeteMissionGroup group;

    private final String name;

    private final CompeteMissionLocker locker;

    private final CompleteMissionConfig config;

    private String target;

    public CompeteMissionTask(CompeteMissionGroup group, String name, CompeteMissionLocker locker,
            CompleteMissionConfig config) {
        this.target = "";
        this.group = group;
        this.name = name;
        this.locker = locker;
        this.config = config;
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

        List<CompeteMission> missions = group.getMissions();
        if (CollectionUtils.isEmpty(missions)) {
            LOG.info("missions empty");
            return;
        }

        for (CompeteMission mission : missions) {
            if (locker.lock(mission.target(), name, config.getTaskLockMillis()) == false) {
                continue;
            }

            target = mission.target();
            mission.run();
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
        List<CompeteMission> missions = group.getMissions();
        if (CollectionUtils.isEmpty(missions)) {
            return;
        }

        for (CompeteMission mission : missions) {
            if (SpringString.isEmpty(locker.get(mission.target()))) {
                LOG.warn("doCheck target:{} no runner", mission.target());
            }
        }
    }
}
