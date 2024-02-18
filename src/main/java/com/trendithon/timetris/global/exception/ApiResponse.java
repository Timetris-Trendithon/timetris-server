package com.trendithon.timetris.global.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import com.trendithon.timetris.global.exception.enums.SuccessStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 응답코드만
    public static <T> ApiResponse<T> success(SuccessStatus successStatus) {
        return new ApiResponse<>(successStatus.getHttpStatusCode(), successStatus.getMessage());
    }

    public static <T> ApiResponse<T> failure(ErrorStatus errorStatus) {
        return new ApiResponse<>(errorStatus.getHttpStatusCode(), errorStatus.getMessage());
    }

    // 응답코드 + 리턴 데이터
    public static <T> ApiResponse<T> success(SuccessStatus successStatus, T data) {
        return new ApiResponse<>(successStatus.getHttpStatusCode(), successStatus.getMessage(), data);
    }

    // 리턴 데이터 (기본 응답 : OK)
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(SuccessStatus.OK.getCode() , SuccessStatus.OK.getMessage(), data);
    }

    public static ApiResponse of(String code, String message){
        return ApiResponse.builder()
                .code(code)
                .message(message)
                .build();
    }
}