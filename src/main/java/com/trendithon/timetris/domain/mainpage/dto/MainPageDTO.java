package com.trendithon.timetris.domain.mainpage.dto;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.See;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class MainPageDTO {

    List<PlanViewDTO> planViewDTOList;
    List<DoViewDTO> doViewDTOList;
    List<SeeViewDTO> seeViewDTO;
    String userName;
    String accessToken;

    public static MainPageDTO from(String accessToken, String username, List<Plan> planList, List<Do> doList, List<See> seeList){
        List<PlanViewDTO> planViewDTOS = planList.stream()
                .map(PlanViewDTO::of)
                .toList();
        List<DoViewDTO> doViewDTOS = doList.stream()
                .map(DoViewDTO::of)
                .toList();
        List<SeeViewDTO> seeViewDTO1 = seeList.stream()
                .map(SeeViewDTO::of)
                .toList();

        return new MainPageDTO(planViewDTOS, doViewDTOS, seeViewDTO1, username, accessToken);

    }

}
