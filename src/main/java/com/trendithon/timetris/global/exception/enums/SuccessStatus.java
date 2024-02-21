package com.trendithon.timetris.global.exception.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum SuccessStatus {

    /**
     * 200 OK
     */
    OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    DELETE_USER_SUCCESS(HttpStatus.OK, "USER200", "유저 탈퇴 성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    public String getHttpStatusCode() {
        return code;
    }
}
