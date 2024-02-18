package com.trendithon.timetris.domain.member.controller;

import com.trendithon.timetris.domain.member.dto.MyPageResponse;
import com.trendithon.timetris.domain.member.service.MyPageService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final TokenProvider tokenProvider;
    private final MyPageService myPageService;

    @GetMapping
    public ApiResponse<MyPageResponse.getMyPageDTO> getMyPage(HttpServletRequest request) {

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

        MyPageResponse.getMyPageDTO myPage = myPageService.getMyPage(userId);

        return ApiResponse.success(SuccessStatus.OK, myPage);

    }
}
