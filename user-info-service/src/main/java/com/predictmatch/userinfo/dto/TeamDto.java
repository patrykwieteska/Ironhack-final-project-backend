package com.predictmatch.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamDto {
    private Long id;
    private String name;
    private String logo;
    private String status;

    public TeamDto(Long id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.status="VERIFIED";
    }
}
