package com.trendithon.timetris.domain.past.service;

import com.trendithon.timetris.domain.mainpage.domain.*;
import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.repository.*;
import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.domain.past.dto.PastViewDTO;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PathServiceImpl implements PastService{

    private final DateRepository dateRepository;
    private final UserRepository userRepository;
    private final UserDateRepository userDateRepository;
    private final PlanRepository planRepository;
    private final DoRepository doRepository;
    private final SeeRepository seeRepository;

    @Override
    public List<PastViewDTO> readPastsAll(long userId) {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));
        List<Date> dateList = dateRepository.findAllByYearAndMonth(year, month);
        List<PastViewDTO> result = new ArrayList<>();
        dateList.forEach(date -> {
                    UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
                    List<Plan> planList = planRepository.findAllByUserDate(userDate);
                    List<Do> doList = doRepository.findAllByUserDate(userDate);
                    List<See> see = seeRepository.findByUserDate(userDate);
                    result.add(PastViewDTO.from(date, planList, doList, see));
                });
        return result;
    }
}
