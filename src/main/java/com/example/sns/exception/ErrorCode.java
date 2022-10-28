package com.example.sns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Duplicate username" ),
    ;

    // 에러코드 마다 http status 정의 위한 용도
    private HttpStatus status;
    private String message;
}
