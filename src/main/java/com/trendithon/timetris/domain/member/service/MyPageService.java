package com.trendithon.timetris.domain.member.service;

import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.dto.MyPageResponse;
import com.trendithon.timetris.domain.member.repository.UserRepository;
import com.trendithon.timetris.global.exception.CustomException;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final UserRepository userRepository;

    public MyPageResponse.getMyPageDTO getMyPage(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        MyPageResponse.getMyPageDTO toMyPageDTO = MyPageResponse.getMyPageDTO.builder()
                .name(user.get().getName())
                .email(user.get().getEmail())
                .build();
        return toMyPageDTO;
    }
}
