package com.predictmatch.userinfo.dao;

import com.predictmatch.userinfo.dao.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;

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

    @Column(unique = true)
    private String username;
    @Nullable
    private String city;
    @Nullable
    private String country;
    @Nullable
    private String info;

    @Email
    @Column(unique =true)
    private String email;

    private long favoriteTeamId;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;
}
