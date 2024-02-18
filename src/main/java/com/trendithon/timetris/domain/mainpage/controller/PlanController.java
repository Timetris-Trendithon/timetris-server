package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Plan;
import com.trendithon.timetris.domain.mainpage.dto.PlanCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.PlanViewDTO;
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
    private final TokenProvider tokenProvider;

    @PostMapping("")
    public ApiResponse createPlan(HttpServletRequest request,
                                        @RequestBody PlanRequestDTO planRequestDTO)
    {
        String accessToken = tokenProvider.extractAccessToken(request).orElse(null);

        Long userId = null;

        if (accessToken != null) {
            userId = tokenProvider.extractId(accessToken).orElse(null);
        } else {
            return ApiResponse.failure(ErrorStatus.NOT_LOGIN_ERROR);

        }
        if(userId == null) {
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }

        Plan plan = planService.createPlan(userId, planRequestDTO);
        if (plan == null){
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{planId}")
    public ApiResponse updatePlan(HttpServletRequest request,
                                  @PathVariable long planId,
                                  @RequestBody PlanViewDTO planViewDTO)
    {
        String accessToken = tokenProvider.extractAccessToken(request).orElse(null);

        Long userId = null;

        if (accessToken != null) {
            userId = tokenProvider.extractId(accessToken).orElse(null);
        } else {
            return ApiResponse.failure(ErrorStatus.NOT_LOGIN_ERROR);

        }
        if(userId == null) {
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }

        planService.updatePlan(userId, planId, planViewDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{planId}")
    public ApiResponse deletePlan(HttpServletRequest request,
                                  @PathVariable long planId)
    {
        String accessToken = tokenProvider.extractAccessToken(request).orElse(null);

        Long userId = null;

        if (accessToken != null) {
            userId = tokenProvider.extractId(accessToken).orElse(null);
        } else {
            return ApiResponse.failure(ErrorStatus.NOT_LOGIN_ERROR);

        }
        if(userId == null) {
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }

        planService.deletePlan(userId, planId);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{planId}/check")
    public ApiResponse donePlan(HttpServletRequest request,
                                @PathVariable long planId)
    {
        String accessToken = tokenProvider.extractAccessToken(request).orElse(null);

        Long userId = null;

        if (accessToken != null) {
            userId = tokenProvider.extractId(accessToken).orElse(null);
        } else {
            return ApiResponse.failure(ErrorStatus.NOT_LOGIN_ERROR);

        }
        if(userId == null) {
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }

        planService.donePlan(userId, planId);
        return ApiResponse.success(SuccessStatus.OK);
    }



}
