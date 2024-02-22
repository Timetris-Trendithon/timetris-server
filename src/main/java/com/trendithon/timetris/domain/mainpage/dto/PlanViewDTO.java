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

    private long id;
    private String title;
    private PlanTimeDTO startTime;
    private PlanTimeDTO endTime;
    private boolean status;
    private CategoryViewDTO category;

    public static PlanViewDTO of(Plan plan){
        return PlanViewDTO.builder()
                .id(plan.getId())
                .title(plan.getTitle())
                .startTime(PlanTimeDTO.of(plan.getStartTime()))
                .endTime(PlanTimeDTO.of(plan.getEndTime()))
                .status(plan.isStatus())
                .category(CategoryViewDTO.of(plan.getCategory()))
                .build();
    }

}
