package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class PlanCreateDTO {

    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean status;
    private Category category;

}
