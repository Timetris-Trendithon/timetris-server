package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.domain.Date;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import com.trendithon.timetris.domain.mainpage.dto.PlanCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanViewDTO;
import com.trendithon.timetris.domain.mainpage.repository.CategoryRepository;
import com.trendithon.timetris.domain.mainpage.repository.DateRepository;
import com.trendithon.timetris.domain.mainpage.repository.PlanRepository;
import com.trendithon.timetris.domain.mainpage.repository.UserDateRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Plan createPlan(long userId, PlanRequestDTO planRequestDTO) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
        Category category = categoryRepository.findById(planRequestDTO.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorStatus.CATEGORY_NOT_FOUND_ERROR));
        String[] startTime = planRequestDTO.getStartTime().split(":");
        LocalTime localStartTime = LocalTime.of(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
        String[] endTime = planRequestDTO.getEndTime().split(":");
        LocalTime localEndTime = LocalTime.of(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
        PlanCreateDTO planCreateDTO = new PlanCreateDTO(planRequestDTO.getTitle(), localStartTime, localEndTime, false, category);
        Plan plan = new Plan(planCreateDTO, userDate);
        return planRepository.save(plan);
    }

    @Override
    public void updatePlan(long userId, long planId, PlanRequestDTO planRequestDTO) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        long categoryId = planRequestDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorStatus.CATEGORY_NOT_FOUND_ERROR));
        if (plan.getUserDate().getUser().getId() != userId) {
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        String[] startTime = planRequestDTO.getStartTime().split(":");
        LocalTime localStartTime = LocalTime.of(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
        String[] endTime = planRequestDTO.getEndTime().split(":");
        LocalTime localEndTime = LocalTime.of(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
        plan.updatePlan(planRequestDTO.getTitle(), localStartTime, localEndTime, category);
        planRepository.save(plan);
    }

    @Override
    public void deletePlan(long userId, long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        if (plan.getUserDate().getUser().getId() != userId) {
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        planRepository.delete(plan);
    }

    @Override
    public void donePlan(long userId, long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        if (plan.getUserDate().getUser().getId() != userId) {
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        plan.donePlan();
        planRepository.save(plan);
    }
}
