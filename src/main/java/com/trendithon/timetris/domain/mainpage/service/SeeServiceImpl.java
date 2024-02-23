package com.trendithon.timetris.domain.mainpage.service;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.domain.Date;
import com.trendithon.timetris.domain.mainpage.domain.See;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import com.trendithon.timetris.domain.mainpage.dto.CategoryViewDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeCreateDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeRequestDTO;
import com.trendithon.timetris.domain.mainpage.dto.SeeViewDTO;
import com.trendithon.timetris.domain.mainpage.repository.CategoryRepository;
import com.trendithon.timetris.domain.mainpage.repository.DateRepository;
import com.trendithon.timetris.domain.mainpage.repository.SeeRepository;
import com.trendithon.timetris.domain.mainpage.repository.UserDateRepository;
import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SeeServiceImpl implements SeeService{

    private final SeeRepository seeRepository;
    private final DateRepository dateRepository;
    private final UserDateRepository userDateRepository;
    private final UserRepository userRepository;

    @Override
    public See createSee(long userId, SeeRequestDTO seeRequestDTO) {
        findUser(userId);
        LocalDate localDate = LocalDate.now();
        Date date = dateRepository.findByDate(localDate);
        UserDate userDate = userDateRepository.findByUser_IdAndDate_Id(userId, date.getId());
        if (!seeRepository.findByUserDate(userDate).isEmpty()){
            throw new CustomException(ErrorStatus.SEE_ALREADY_EXISTS_ERROR);
        }
        SeeCreateDTO seeCreateDTO = new SeeCreateDTO(seeRequestDTO.getContent());
        See see = new See(seeCreateDTO, userDate);
        return seeRepository.save(see);
    }

    @Override
    public void updateSee(long userId, long seeId, SeeRequestDTO seeRequestDTO) {
        findUser(userId);
        See see = seeRepository.findById(seeId)
                .orElseThrow(() -> new CustomException(ErrorStatus.SEE_NOT_FOUND_ERROR));
        if (see.getUserDate().getUser().getId() != userId){
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        see.updateSee(seeRequestDTO.getContent());
        seeRepository.save(see);
    }

    @Override
    public void deleteSee(long userId, long seeId) {
        findUser(userId);
        See see = seeRepository.findById(seeId)
                .orElseThrow(() -> new CustomException(ErrorStatus.SEE_NOT_FOUND_ERROR));
        if (see.getUserDate().getUser().getId() != userId){
            throw new CustomException(ErrorStatus.NO_PERMISSION_ERROR);
        }
        seeRepository.delete(see);
    }

    public void findUser(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorStatus.USER_NOT_FOUND_ERROR));
    }
}
