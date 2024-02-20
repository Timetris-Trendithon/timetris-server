package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Cycle;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.Week;
import com.trendithon.timetris.domain.mainpage.dto.CycleCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.CycleRequestDTO;
import com.trendithon.timetris.domain.mainpage.repository.CycleRepository;
import com.trendithon.timetris.domain.mainpage.repository.PlanRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CycleServiceImpl implements CycleService{

    private final CycleRepository cycleRepository;
    private final PlanRepository planRepository;

    @Override
    public List<String> createCycle(Plan plan, CycleRequestDTO cycleRequestDTO) {
        List<String> cycleList = cycleRequestDTO.getCycling();
        for (String cycle : cycleList){
            Cycle cycle1 = new Cycle(Week.valueOf(cycle), plan);
            cycleRepository.save(cycle1);
        }
        return cycleList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> readCycle(long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        List<Cycle> cycleList = cycleRepository.findAllByPlan_Id(plan.getId());
        List<String> stringList = new ArrayList<>();
        for (Cycle cycle : cycleList){
            stringList.add(cycle.getCycling().toString());
        }
        return stringList;
    }

    @Override
    public void updateCycle(long planId, CycleRequestDTO cycleRequestDTO) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        List<String> cycleNewStringList = cycleRequestDTO.getCycling();
        List<Cycle> cycleList = cycleRepository.findAllByPlan_Id(planId);
        ArrayList<String> cycleOriginStringList = new ArrayList<>();
        for (Cycle cycle : cycleList){
            cycleOriginStringList.add(cycle.getCycling().toString());
        }
        ArrayList<String> newCycle = new ArrayList<>(cycleNewStringList);
        newCycle.removeAll(cycleOriginStringList);
        ArrayList<String> oldCycle = new ArrayList<>(cycleOriginStringList);
        oldCycle.removeAll(cycleNewStringList);

        for (String cycle : newCycle){
            Cycle cycle1 = new Cycle(Week.valueOf(cycle), plan);
            cycleRepository.save(cycle1);
        }
        for (String cycle : oldCycle){
            cycleRepository.deleteByPlan_IdAndCyclingIs(planId, Week.valueOf(cycle));
        }
    }
}
