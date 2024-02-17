package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.dto.PlanCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanViewDTO;
import com.trendithon.timetris.domain.mainpage.service.PlanService;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    @PostMapping("")
    public ApiResponse createPlan(Authentication authentication,
                                        @RequestBody PlanRequestDTO planRequestDTO)
    {
        long userId = 1;
        Plan plan = planService.createPlan(userId, planRequestDTO);
        if (plan == null){
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{planId}")
    public ApiResponse updatePlan(Authentication authentication,
                                  @PathVariable long planId,
                                  @RequestBody PlanViewDTO planViewDTO)
    {
        long userId = 1;
        planService.updatePlan(userId, planId, planViewDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ApiResponse deletePlan(Authentication authentication,
                                  @PathVariable long planId)
    {
        long userId = 1;
        planService.deletePlan(userId, planId);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{planId}/check")
    public ApiResponse donePlan(Authentication authentication,
                                @PathVariable long planId)
    {
        long userId = 1;
        planService.donePlan(userId, planId);
        return ApiResponse.success(SuccessStatus.OK);
    }



}
