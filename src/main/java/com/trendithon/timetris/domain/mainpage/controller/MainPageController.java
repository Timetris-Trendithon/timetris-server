package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.service.MainPageService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageController {

    private final MainPageService mainPageService;
    private final TokenProvider tokenProvider;

    @GetMapping("/{userId}")
    public ApiResponse<MainPageDTO> getMainPage(Authentication authentication,
                                                @PathVariable long userId) {
        MainPageDTO mainPageDTO = mainPageService.getMainPage(userId);
        return ApiResponse.success(SuccessStatus.OK, mainPageDTO);
    }


    @GetMapping
    public ApiResponse<String> MainForTest(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("name");

        if (userName == null) {
            return ApiResponse.of("LOGIN", "로그인 하세요");
        } else {
            return ApiResponse.success(SuccessStatus.OK, userName);
        }

    }
}
