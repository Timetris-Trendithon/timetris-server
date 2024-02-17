package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.*;
import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService{

    private final PlanRepository planRepository;
    private final DoRepository doRepository;
    private final SeeRepository seeRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;

    @Override
    public MainPageDTO getMainPage(long userId) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());

        List<Plan> planList = planRepository.findAllByUserDate(userDate);
        List<Do> doList = doRepository.findAllByUserDate(userDate);
        List<See> see = seeRepository.findByUserDate(userDate);

        return MainPageDTO.from(planList, doList, see);
    }
}