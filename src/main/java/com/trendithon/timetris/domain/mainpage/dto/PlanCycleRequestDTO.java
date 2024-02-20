package com.trendithon.timetris.domain.mainpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class PlanCycleRequestDTO {

    private PlanRequestDTO planRequestDTO;
    private CycleRequestDTO cycleRequestDTO;

}
