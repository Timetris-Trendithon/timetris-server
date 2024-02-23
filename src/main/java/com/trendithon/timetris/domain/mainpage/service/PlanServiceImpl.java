package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.*;
import com.trendithon.timetris.domain.mainpage.dto.*;
import com.trendithon.timetris.domain.mainpage.repository.*;
import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Plan createPlan(long userId, PlanRequestDTO planRequestDTO) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        findUser(userId);
        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
        Category category = categoryRepository.findById(planRequestDTO.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorStatus.CATEGORY_NOT_FOUND_ERROR));
        String[] startTime = planRequestDTO.getStartTime().split(":");
        LocalTime localStartTime = LocalTime.of(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
        String[] endTime = planRequestDTO.getEndTime().split(":");
        LocalTime localEndTime = LocalTime.of(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
        PlanCreateDTO planCreateDTO = new PlanCreateDTO(planRequestDTO.getTitle(), localStartTime, localEndTime, false, category);
        Plan plan = new Plan(planCreateDTO, userDate);
        planRepository.save(plan);
        return plan;
    }

    @Override
    public void updatePlan(long userId, long planId, PlanRequestDTO planRequestDTO) {
        findUser(userId);
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
        findUser(userId);
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        if (plan.getUserDate().getUser().getId() != userId) {
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        planRepository.delete(plan);
    }

    @Override
    public void donePlan(long userId, long planId) {
        findUser(userId);
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
        if (plan.getUserDate().getUser().getId() != userId) {
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        plan.donePlan();
        planRepository.save(plan);
    }

    public void findUser(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));
    }
}
