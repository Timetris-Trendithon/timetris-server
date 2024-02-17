package com.trendithon.timetris.global.auth.oauth.dto;

import com.trendithon.timetris.domain.login.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getProfile();
    }
}