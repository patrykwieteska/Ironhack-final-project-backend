package com.predictmatch.liveresults.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class League {

    @Id
    private Long externalLeagueId;

    private String leagueName;

    private Integer season;

    private String logo;

    private String country;

    private String countryFlag;
}
