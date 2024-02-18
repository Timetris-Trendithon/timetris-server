package com.trendithon.timetris.global.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    //INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "COMMON400", "올바르지 않은 파라미터입니다."),
    INVALID_FORMAT_ERROR(HttpStatus.BAD_REQUEST,"COMMON400", "올바르지 않은 포맷입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, "COMMON400", "올바르지 않은 타입입니다."),
    ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "COMMON400", "필수 파라미터가 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    INVALID_HTTP_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "COMMON400", "잘못된 Http Method 요청입니다."),
    NO_PERMISSION_ERROR(HttpStatus.BAD_REQUEST, "COMMOM400", "해당 유저에게 수정/삭제 권한이 없습니다."),
    CATEGORY_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "COMMOM400", "해당 카테고리를 찾을 수 없습니다."),
    PLAN_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "COMMOM400", "해당 plan을 찾을 수 없습니다."),
    DO_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "COMMOM400", "해당 do를 찾을 수 없습니다."),
    SEE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "COMMOM400", "해당 see를 찾을 수 없습니다."),

    // 회원 관련 에러
    USER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "USER400", "해당 유저를 찾을 수 없습니다."),
    NOT_LOGIN_ERROR(HttpStatus.NOT_FOUND, "LOGIN400", "로그인 해주세요.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public String getHttpStatusCode() {
        return code;
    }
}

