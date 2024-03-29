package com.trendithon.timetris.domain.member.domain;

import com.trendithon.timetris.domain.mainpage.domain.Category;
import com.trendithon.timetris.domain.mainpage.domain.UserDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String nickname;

    @NotNull
    private String email;

    @Column
    private String profile;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String refreshToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDate> userDateList = new ArrayList<>();

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    // 로그인 할 때마다 해당 필드 새롭게 업데이트
    public User update(String name, String email, String profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    // 유저 권한설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }


    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
