package com.trendithon.timetris.domain.mainpage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PlanTimeDTO {

    private int hour;

    public static PlanTimeDTO of(LocalTime localTime){
        return PlanTimeDTO.builder()
                .hour(localTime.getHour())
                .build();
    }

}
