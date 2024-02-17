package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PlanViewDTO {

    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean status;
    private Category category;

    public static PlanViewDTO of(Plan plan){
        return PlanViewDTO.builder()
                .title(plan.getTitle())
                .startTime(plan.getStartTime())
                .endTime(plan.getEndTime())
                .status(plan.isStatus())
                .category(plan.getCategory())
                .build();
    }

}
