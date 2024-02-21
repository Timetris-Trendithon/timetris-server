package com.trendithon.timetris.domain.member.service;

import com.trendithon.timetris.domain.member.domain.User;
import com.trendithon.timetris.domain.member.dto.MyPageResponse;
import com.trendithon.timetris.domain.member.dto.UpdateNameRequest;
import com.trendithon.timetris.domain.member.repository.UserRepository;
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
                .name(user.get().getNickname())
                .email(user.get().getEmail())
                .build();
        return toMyPageDTO;
    }

    @Transactional
    public MyPageResponse.getMyPageDTO updateName(Long userId, UpdateNameRequest nameRequest) {
        Optional<User> user = userRepository.findById(userId);
        String nickname = nameRequest.getNickname();
        user.get().changeNickname(nickname);

        return MyPageResponse.getMyPageDTO.builder()
                .name(user.get().getNickname())
                .email(user.get().getEmail())
                .build();

    }
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }

}
