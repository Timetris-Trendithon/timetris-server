package com.trendithon.timetris.domain.member.controller;

import com.trendithon.timetris.domain.member.dto.MyPageResponse;
import com.trendithon.timetris.domain.member.dto.UpdateNameRequest;
import com.trendithon.timetris.domain.member.service.MyPageService;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final TokenProvider tokenProvider;
    private final MyPageService myPageService;

    @GetMapping
    @Operation(summary = "마이페이지 조회 API")
    public ApiResponse<MyPageResponse.getMyPageDTO> getMyPage(HttpServletRequest request) {

        Long userId = tokenProvider.getUserId(request);

        MyPageResponse.getMyPageDTO myPage = myPageService.getMyPage(userId);

        return ApiResponse.success(SuccessStatus.OK, myPage);

    }

    @PatchMapping
    @Operation(summary = "이름 수정 API")
    public ApiResponse<MyPageResponse.getMyPageDTO> updateName(@RequestBody UpdateNameRequest nameRequest,
                                                               HttpServletRequest request) {
        Long userId = tokenProvider.getUserId(request);

        MyPageResponse.getMyPageDTO myPageDTO = myPageService.updateName(userId, nameRequest);
        return ApiResponse.success(SuccessStatus.OK, myPageDTO);

    }

    @PostMapping
    @Operation(summary = "회원 탈퇴 API")
    public ApiResponse<?> userOutService(HttpServletRequest request) {
        Long userId = tokenProvider.getUserId(request);
        myPageService.deleteUser(userId);
        return ApiResponse.success(SuccessStatus.DELETE_USER_SUCCESS);
    }
}
