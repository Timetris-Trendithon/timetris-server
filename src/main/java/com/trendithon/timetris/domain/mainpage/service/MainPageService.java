package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.member.dto.MyPageResponse;
import org.springframework.stereotype.Service;



@Service
public interface MainPageService {
    MainPageDTO getMainPage(long userId);

    MyPageResponse.getMyPageDTO getUserInfo(Long userId);

    void createUserDate();
}
