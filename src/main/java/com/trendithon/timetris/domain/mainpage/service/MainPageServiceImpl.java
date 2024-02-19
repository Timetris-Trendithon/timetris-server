package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.domain.mainpage.domain.*;
import com.trendithon.timetris.domain.mainpage.dto.DateCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.dto.UserDateCreateDTO;
import com.trendithon.timetris.domain.mainpage.repository.*;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final UserRepository userRepository;

    @Override
    public MainPageDTO getMainPage(long userId, HttpServletRequest request) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);

        String userName = (String) request.getSession().getAttribute("name");
        String accessToken = (String) request.getSession().getAttribute("token");

        if (userRepository.findById(userId).isEmpty()){
            throw new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR);
        }
        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());

        List<Plan> planList = planRepository.findAllByUserDate(userDate);
        List<Do> doList = doRepository.findAllByUserDate(userDate);
        List<See> see = seeRepository.findByUserDate(userDate);

        return MainPageDTO.from(accessToken, userName, planList, doList, see);
    }
    @Override
    public Long getUserId(String userName, String imgUrl) {
        Optional<User> user = userRepository.findByNameAndProfile(userName, imgUrl);
        return user.get().getId();
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void createUserDate() {
        List<User> users = userRepository.findAll();
        LocalDate localDate = LocalDate.now();
        DateCreateDTO dateCreateDTO = new DateCreateDTO(localDate);
        Date date = new Date(dateCreateDTO);
        dateRepository.save(date);
        users.stream()
                .forEach(user -> {
                    UserDateCreateDTO userDateCreateDTO = new UserDateCreateDTO(user, date);
                    UserDate userDate = new UserDate(userDateCreateDTO);
                    userDateRepository.save(userDate);
                });
    }
}
