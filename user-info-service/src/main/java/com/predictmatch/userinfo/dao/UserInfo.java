package com.predictmatch.userinfo.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfo {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String info;
    private String email;

    @ManyToOne
    @JoinColumn(name = "favorite_team_id")
    private Team favoriteTeam;



}
