package com.trendithon.timetris.global.exception;

import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorStatus errorStatus;


    public CustomException(ErrorStatus errorStatus, String message) {
        super(message);
        this.errorStatus = errorStatus;
    }

    public String getHttpStatus() {
        return errorStatus.getHttpStatusCode();
    }
}
