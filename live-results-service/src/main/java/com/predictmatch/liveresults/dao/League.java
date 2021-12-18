package com.predictmatch.liveresults.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

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

    @OneToMany( cascade = {CascadeType.ALL}, mappedBy = "leagueId")
    private List<LeagueStanding> standings;
}
