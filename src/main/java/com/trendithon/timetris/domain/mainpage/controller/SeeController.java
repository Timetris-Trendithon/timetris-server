package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.See;
import com.trendithon.timetris.domain.mainpage.dto.SeeCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeViewDTO;
import com.trendithon.timetris.domain.mainpage.service.SeeService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/see")
public class SeeController {

    private final SeeService seeService;
    private final TokenProvider tokenProvider;

    public long getUserId(HttpServletRequest request){
        String accessToken = tokenProvider.extractAccessToken(request).orElse(null);

        Long userId = null;

        if (accessToken != null) {
            userId = tokenProvider.extractId(accessToken).orElse(null);
        } else {
            throw new CustomException(ErrorStatus.NOT_LOGIN_ERROR);

        }
        if(userId == null) {
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }
        return userId;
    }

    @PostMapping("")
    public ApiResponse createSee(HttpServletRequest request,
                                 @RequestBody SeeRequestDTO seeRequestDTO)
    {
        long userId = getUserId(request);

        See see = seeService.createSee(userId, seeRequestDTO);
        if (see == null){
            throw new CustomException(ErrorStatus.SEE_NOT_FOUND_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{seeId}")
    public ApiResponse updateSee(HttpServletRequest request,
                                 @PathVariable long seeId,
                                 @RequestBody SeeRequestDTO seeRequestDTO)
    {
        long userId = getUserId(request);

        seeService.updateSee(userId, seeId, seeRequestDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{seeId}")
    public ApiResponse deleteSee(HttpServletRequest request,
                                 @PathVariable long seeId)
    {
        long userId = getUserId(request);

        seeService.deleteSee(userId, seeId);
        return ApiResponse.success(SuccessStatus.OK);
    }

}
