package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface MainPageService {
    MainPageDTO getMainPage(long userId);
    void createUserDate();
}
