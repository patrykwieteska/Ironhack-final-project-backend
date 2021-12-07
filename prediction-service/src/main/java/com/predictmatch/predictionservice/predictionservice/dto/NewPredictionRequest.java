package com.predictmatch.predictionservice.predictionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.style.ToStringCreator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPredictionRequest {
    private Long fixtureId;
    private String username;
    private Integer predictedHomeGoals;
    private Integer predictedAwayGoals;


    @Override
    public String toString() {
        return new ToStringCreator( this )
                .append( "fixtureId", fixtureId )
                .append( "username", username )
                .append( "predictedHomeGoals", predictedHomeGoals )
                .append( "predictedAwayGoals", predictedAwayGoals )
                .toString();
    }
}
