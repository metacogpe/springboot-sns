package com.example.sns.exception;

import com.example.sns.controller.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외 던진 것을 Response 에서 반영해서 내리는 것 구현
// ControllerAdvice 가 예외를 잡아서 보내 주는 구현 필요
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(SnsApplicationException.class)
    public ResponseEntity<?> applicationHandler(SnsApplicationException e) {

        log.error("Errors occurs {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }
}
