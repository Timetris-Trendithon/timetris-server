package com.trendithon.timetris.domain.mainpage.controller;

import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.service.MainPageService;
import com.trendithon.timetris.global.exception.ApiResponse;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
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

    @GetMapping("/{userId}")
    public ApiResponse<MainPageDTO> getMainPage(Authentication authentication,
                                                @PathVariable long userId)
    {
        MainPageDTO mainPageDTO = mainPageService.getMainPage(userId);
        return ApiResponse.success(SuccessStatus.OK, mainPageDTO);
    }

}
