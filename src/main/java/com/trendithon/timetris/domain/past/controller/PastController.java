package com.trendithon.timetris.domain.past.controller;

import com.trendithon.timetris.domain.past.dto.PastViewDTO;
import com.trendithon.timetris.domain.past.service.PastService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/past")
public class PastController {

    private final PastService pastService;
    private final TokenProvider tokenProvider;

    @GetMapping("")
    public ApiResponse<List<PastViewDTO>> readPastsController(HttpServletRequest request){
        long userId = tokenProvider.getUserId(request);
        List<PastViewDTO> pastViewDTOList = pastService.readPastsAll(userId);
        return ApiResponse.success(SuccessStatus.OK, pastViewDTOList);
    }

}
