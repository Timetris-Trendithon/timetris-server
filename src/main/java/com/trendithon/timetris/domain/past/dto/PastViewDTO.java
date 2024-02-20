package com.trendithon.timetris.domain.past.dto;

import com.trendithon.timetris.domain.mainpage.domain.Date;
import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.See;
import com.trendithon.timetris.domain.mainpage.dto.DoViewDTO;
import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanViewDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeViewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class PastViewDTO {

    LocalDate localDate;
    List<PlanViewDTO> planViewDTOList;
    List<DoViewDTO> doViewDTOList;
    List<SeeViewDTO> seeViewDTO;

    public static PastViewDTO from(Date date, List<Plan> planList, List<Do> doList, List<See> seeList){
        LocalDate localDate1 = date.getDate();
        List<PlanViewDTO> planViewDTOS = planList.stream()
                .map(PlanViewDTO::of)
                .toList();
        List<DoViewDTO> doViewDTOS = doList.stream()
                .map(DoViewDTO::of)
                .toList();
        List<SeeViewDTO> seeViewDTO1 = seeList.stream()
                .map(SeeViewDTO::of)
                .toList();
        return new PastViewDTO(localDate1, planViewDTOS, doViewDTOS, seeViewDTO1);
    }
}
