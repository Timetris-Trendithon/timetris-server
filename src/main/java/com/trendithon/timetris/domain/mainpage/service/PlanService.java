package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.dto.PlanCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanViewDTO;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {

    Plan createPlan(long userId, PlanRequestDTO planRequestDTO);
    void updatePlan(long userId, long planId, PlanRequestDTO planRequestDTO);
    void deletePlan(long userId, long planId);
    void donePlan(long userId, long planId);

}
