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
    INVALID_HTTP_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "COMMON400", "잘못된 Http Method 요청입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public String getHttpStatusCode() {
        return code;
    }
}

