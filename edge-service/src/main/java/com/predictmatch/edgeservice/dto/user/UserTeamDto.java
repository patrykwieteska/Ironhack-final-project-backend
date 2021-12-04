package com.predictmatch.edgeservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserTeamDto {
    private Long id;
    private String name;
    private String logo;
    private String status;
}
