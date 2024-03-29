package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.dto.DoCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoViewDTO;
import com.trendithon.timetris.domain.mainpage.service.DoService;
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
@RequestMapping("/do")
public class DoController {

    private final DoService doService;
    private final TokenProvider tokenProvider;


    @PostMapping("")
    public ApiResponse createDo(HttpServletRequest request,
                                @RequestBody DoRequestDTO doRequestDTO) {
        Long userId = tokenProvider.getUserId(request);

        Do done = doService.createDo(userId, doRequestDTO);
        if (done == null) {
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{doId}")
    public ApiResponse updateDo(HttpServletRequest request,
                                @PathVariable long doId,
                                @RequestBody DoRequestDTO doRequestDTO) {
        Long userId = tokenProvider.getUserId(request);

        doService.updateDo(userId, doId, doRequestDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{doId}")
    public ApiResponse deleteDo(HttpServletRequest request,
                                @PathVariable long doId) {
        Long userId = tokenProvider.getUserId(request);

        doService.deleteDo(userId, doId);
        return ApiResponse.success(SuccessStatus.OK);
    }

}
