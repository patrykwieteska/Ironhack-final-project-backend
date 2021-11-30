package com.predictmatch.liveresults.mapper;

import com.predictmatch.liveresults.enmus.FixtureStatus;

public class FixtureStatusMapper {

    public static FixtureStatus fixtureStatusToEnum(String fixtureStatus) {

        return switch (fixtureStatus) {
            case "1H", "2H", "HT" -> FixtureStatus.LIVE;
            case "NS", "TBD" -> FixtureStatus.UPCOMING;
            case "FT" -> FixtureStatus.FINISHED;
            case "PST" -> FixtureStatus.POSTPONED;
            default -> FixtureStatus.UNKNOWN;
        };
    }
}
