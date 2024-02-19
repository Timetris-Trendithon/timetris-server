package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


@Service
public interface MainPageService {
    MainPageDTO getMainPage(long userId, HttpServletRequest request);

    Long getUserId(String username, String imgUrl);
    void createUserDate();
}
