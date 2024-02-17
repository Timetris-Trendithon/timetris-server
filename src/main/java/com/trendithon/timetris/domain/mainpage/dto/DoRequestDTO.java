package com.trendithon.timetris.domain.mainpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class DoRequestDTO {

    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private long categoryId;
    private long userDateId;

}
