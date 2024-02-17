package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.domain.Do;
import com.trendithon.timetris.domain.mainpage.dto.DoCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.DoViewDTO;
import com.trendithon.timetris.domain.mainpage.service.DoService;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/do")
public class DoController {

    private final DoService doService;

    @PostMapping("")
    public ApiResponse createDo(Authentication authentication,
                                @RequestBody DoRequestDTO doRequestDTO)
    {
        long userId = 1;
        Do done = doService.createDo(userId, doRequestDTO);
        if (done == null){
            throw new CustomException(ErrorStatus.INVALID_FORMAT_ERROR);
        }
        return ApiResponse.success(SuccessStatus.OK);
    }

    @PutMapping("/{doId}")
    public ApiResponse updateDo(Authentication authentication,
                                @PathVariable long doId,
                                @RequestBody DoViewDTO doViewDTO)
    {
        long userId = 1;
        doService.updateDo(userId, doId, doViewDTO);
        return ApiResponse.success(SuccessStatus.OK);
    }

    @DeleteMapping("/{doId}")
    public ApiResponse deleteDo(Authentication authentication,
                                @PathVariable long doId)
    {
        long userId = 1;
        doService.deleteDo(userId, doId);
        return ApiResponse.success(SuccessStatus.OK);
    }

}
