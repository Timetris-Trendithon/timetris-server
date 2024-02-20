package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Cycle;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.Week;
import com.trendithon.timetris.domain.mainpage.dto.CycleRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CycleService {
    List<String> createCycle(Plan plan, CycleRequestDTO cycleRequestDTO);
    List<String> readCycle(long planId);
    void updateCycle(long planId, CycleRequestDTO cycleRequestDTO);
}
