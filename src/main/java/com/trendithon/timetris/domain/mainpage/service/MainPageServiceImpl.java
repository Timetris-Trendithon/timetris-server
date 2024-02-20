package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.dto.MyPageResponse;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.domain.mainpage.domain.*;
import com.trendithon.timetris.domain.mainpage.dto.DateCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.MainPageDTO;
import com.trendithon.timetris.domain.mainpage.dto.UserDateCreateDTO;
import com.trendithon.timetris.domain.mainpage.repository.*;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainPageServiceImpl implements MainPageService{

    private final PlanRepository planRepository;
    private final DoRepository doRepository;
    private final SeeRepository seeRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;
    private final UserRepository userRepository;

    @Override
    public MainPageDTO getMainPage(long userId) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));

        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
        if (userDate == null){
            UserDateCreateDTO userDateCreateDTO = new UserDateCreateDTO(user, date);
            UserDate userDate1 = new UserDate(userDateCreateDTO);
            userDateRepository.save(userDate1);
        }

        List<Plan> planList = planRepository.findAllByUserDate(userDate);
        List<Do> doList = doRepository.findAllByUserDate(userDate);
        List<See> see = seeRepository.findByUserDate(userDate);
        String name = userRepository.findById(userId).get().getNickname();

        return MainPageDTO.from(name, planList, doList, see);
    }
    @Override
    public MyPageResponse.getMyPageDTO getUserInfo(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return MyPageResponse.getMyPageDTO.builder()
                .name(user.get().getName())
                .email(user.get().getEmail())
                .build();
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void createUserDate() {
        List<User> users = userRepository.findAll();
        LocalDate localDate = LocalDate.now();
        DateCreateDTO dateCreateDTO = new DateCreateDTO(localDate);
        Date date = new Date(dateCreateDTO);
        dateRepository.save(date);
        System.out.println("created Date: "+localDate);
        users.stream()
                .forEach(user -> {
                    UserDateCreateDTO userDateCreateDTO = new UserDateCreateDTO(user, date);
                    UserDate userDate = new UserDate(userDateCreateDTO);
                    userDateRepository.save(userDate);
                });
    }
}
