package com.trendithon.timetris.domain.mainpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class PlanRequestDTO {

    private String title;
    private String startTime;
    private String endTime;
    private long categoryId;

}
