package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Cycle;
import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.dto.*;
import com.trendithon.timetris.domain.mainpage.service.CycleService;
import com.trendithon.timetris.domain.mainpage.service.PlanService;
import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;
    private final CycleService cycleService;
    private final TokenProvider tokenProvider;


    @PostMapping("")
    public ApiResponse createPlan(HttpServletRequest request,
                                  @RequestBody PlanCycleRequestDTO planCycleRequestDTO) {
        Long userId = tokenProvider.getUserId(request);

        PlanRequestDTO planRequestDTO = planCycleRequestDTO.getPlanRequestDTO();
        CycleRequestDTO cycleRequestDTO = planCycleRequestDTO.getCycleRequestDTO();
        Plan plan = planService.createPlan(userId, planRequestDTO);
        if (plan == null) {
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        cycleService.createCycle(plan, cycleRequestDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{planId}")
    public ApiResponse updatePlan(HttpServletRequest request,
                                  @PathVariable long planId,
                                  @RequestBody PlanCycleRequestDTO planCycleRequestDTO) {
        PlanRequestDTO planRequestDTO = planCycleRequestDTO.getPlanRequestDTO();
        CycleRequestDTO cycleRequestDTO = planCycleRequestDTO.getCycleRequestDTO();
        Long userId = tokenProvider.getUserId(request);
        planService.updatePlan(userId, planId, planRequestDTO);
        cycleService.updateCycle(planId, cycleRequestDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ApiResponse deletePlan(HttpServletRequest request,
                                  @PathVariable long planId) {
        Long userId = tokenProvider.getUserId(request);

        planService.deletePlan(userId, planId);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{planId}/check")
    public ApiResponse donePlan(HttpServletRequest request,
                                @PathVariable long planId) {
        Long userId = tokenProvider.getUserId(request);

        planService.donePlan(userId, planId);
        return ApiResponse.success(SuccessStatus.OK);
    }


}
