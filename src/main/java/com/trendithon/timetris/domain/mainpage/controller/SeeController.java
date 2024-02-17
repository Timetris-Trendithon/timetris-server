package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.See;
import com.trendithon.timetris.domain.mainpage.dto.SeeCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeViewDTO;
import com.trendithon.timetris.domain.mainpage.service.SeeService;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/see")
public class SeeController {

    private final SeeService seeService;

    @PostMapping("")
    public ApiResponse createSee(Authentication authentication,
                                 @RequestBody SeeRequestDTO seeRequestDTO)
    {
        long userId = 1;
        See see = seeService.createSee(userId, seeRequestDTO);
        if (see == null){
            throw new CustomException(ErrorStatus.SEE_NOT_FOUND_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{seeId}")
    public ApiResponse updateSee(Authentication authentication,
                                 @PathVariable long seeId,
                                 @RequestBody SeeViewDTO seeViewDTO)
    {
        long userId = 1;
        seeService.updateSee(userId, seeId, seeViewDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{seeId}")
    public ApiResponse deleteSee(Authentication authentication,
                                 @PathVariable long seeId)
    {
        long userId = 1;
        seeService.deleteSee(userId, seeId);
        return ApiResponse.success(SuccessStatus.OK);
    }

}
