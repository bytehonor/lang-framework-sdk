package com.bytehonor.sdk.lang.spring.mission;

import java.util.ArrayList;
import java.util.List;

public class CompeteMissionGroup {

    private final List<CompeteMission> missions;

    public CompeteMissionGroup() {
        this.missions = new ArrayList<CompeteMission>();
    }

    public static CompeteMissionGroup create() {
        return new CompeteMissionGroup();
    }

    public List<CompeteMission> getMissions() {
        return missions;
    }

    public CompeteMissionGroup add(CompeteMission mission) {
        missions.add(mission);
        return this;
    }

}
