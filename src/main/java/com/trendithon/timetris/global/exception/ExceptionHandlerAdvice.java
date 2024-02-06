package com.trendithon.timetris.global.exception;

import com.trendithon.timetris.global.exception.enums.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        ErrorStatus errorCode = ErrorStatus.INTERNAL_SERVER_ERROR;
        ApiResponse errorResponse = ApiResponse.of(errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleSystemException(CustomException e) {
        ErrorStatus errorCode = e.getErrorStatus();
        ApiResponse errorResponse = ApiResponse.of(errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

}
