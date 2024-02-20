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
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MainPageServiceImpl implements MainPageService{

    private final PlanRepository planRepository;
    private final DoRepository doRepository;
    private final SeeRepository seeRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;
    private final UserRepository userRepository;
    private final CycleRepository cycleRepository;

    @Override
    public MainPageDTO getMainPage(long userId) {
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        if (date == null){
            DateCreateDTO dateCreateDTO = new DateCreateDTO(localDate);
            Date date1 = new Date(dateCreateDTO);
            dateRepository.save(date1);
            date = date1;
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));

        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
        if (userDate == null){
            UserDateCreateDTO userDateCreateDTO = new UserDateCreateDTO(user, date);
            UserDate userDate1 = new UserDate(userDateCreateDTO);
            userDateRepository.save(userDate1);

            String dayOfWeek = localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();
            List<Cycle> cycleList = cycleRepository.findAllByUser_IdAndCyclingIs(userId, Week.valueOf(dayOfWeek));
            if (cycleList != null){
                for (Cycle cycle : cycleList){
                    Plan originPlan = planRepository.findById(cycle.getPlan().getId())
                            .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
                    Plan newPlan = new Plan(originPlan, userDate1);
                    planRepository.save(newPlan);
                }
            }
        }

        List<Plan> planList = planRepository.findAllByUserDate(userDate);
        List<Do> doList = doRepository.findAllByUserDate(userDate);
        List<See> see = seeRepository.findByUserDate(userDate);
        String name = user.getNickname();

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

                    String dayOfWeek = localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();
                    List<Cycle> cycleList = cycleRepository.findAllByUser_IdAndCyclingIs(user.getId(), Week.valueOf(dayOfWeek));
                    if (cycleList != null){
                        for (Cycle cycle : cycleList){
                            Plan originPlan = planRepository.findById(cycle.getPlan().getId())
                                    .orElseThrow(() -> new CustomException(ErrorStatus.PLAN_NOT_FOUND_ERROR));
                            Plan newPlan = new Plan(originPlan, userDate);
                            planRepository.save(newPlan);
                        }
                    }
                });
    }
}
