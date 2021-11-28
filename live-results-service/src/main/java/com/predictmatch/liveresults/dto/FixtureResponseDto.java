package com.predictmatch.liveresults.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FixtureResponseDto {

    String message;
    List<FixtureDto> fixtures;

}
