package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.See;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class MainPageDTO {

    List<PlanViewDTO> planViewDTOList;
    List<DoViewDTO> doViewDTOList;
    List<SeeViewDTO> seeViewDTO;

    public static MainPageDTO from(List<Plan> planList, List<Do> doList, List<See> seeList){
        List<PlanViewDTO> planViewDTOS = planList.stream()
                .map(PlanViewDTO::of)
                .toList();
        List<DoViewDTO> doViewDTOS = doList.stream()
                .map(DoViewDTO::of)
                .toList();
        List<SeeViewDTO> seeViewDTO1 = seeList.stream()
                .map(SeeViewDTO::of)
                .toList();

        return new MainPageDTO(planViewDTOS, doViewDTOS, seeViewDTO1);

    }

    public static MainPageDTO from(List<Plan> planList, List<Do> doList){
        List<PlanViewDTO> planViewDTOS = planList.stream()
                .map(PlanViewDTO::of)
                .toList();
        List<DoViewDTO> doViewDTOS = doList.stream()
                .map(DoViewDTO::of)
                .toList();

    }


}