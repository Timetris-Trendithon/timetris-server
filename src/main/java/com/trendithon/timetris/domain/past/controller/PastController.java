package com.trendithon.timetris.domain.past.controller;

import com.trendithon.timetris.domain.past.dto.PastViewDTO;
import com.trendithon.timetris.domain.past.service.PastService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/past")
public class PastController {

    private final PastService pastService;
    private final TokenProvider tokenProvider;

    @GetMapping("")
    public ApiResponse<List<PastViewDTO>> readPasts(HttpServletRequest request){
        long userId = tokenProvider.getUserId(request);
        List<PastViewDTO> pastViewDTOList = pastService.readPastsAll(userId);
        return ApiResponse.success(SuccessStatus.OK, pastViewDTOList);
    }

    @GetMapping("/{date}")
    public ApiResponse<PastViewDTO> readPast(HttpServletRequest request,
                                             @PathVariable String date){
        long userId = tokenProvider.getUserId(request);
        PastViewDTO pastViewDTO = pastService.readPast(userId, date);
        return ApiResponse.success(SuccessStatus.OK, pastViewDTO);
    }

}
