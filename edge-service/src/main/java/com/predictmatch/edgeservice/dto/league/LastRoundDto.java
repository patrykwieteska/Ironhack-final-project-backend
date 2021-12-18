package com.predictmatch.edgeservice.dto.league;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastRoundDto {

    int lastRound;
    int nextRound;

    public LastRoundDto(int lastRound) {
        this.lastRound=lastRound;
        this.nextRound=lastRound+1;
    }
}
