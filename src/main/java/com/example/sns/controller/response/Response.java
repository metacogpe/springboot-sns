package com.example.sns.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultcode;
    private T result; // 결과값 타입이 다를 수 있어 제네릭으로 선언 : UserJoinResponse 타입, LoginResponse 타입 등 다양한 결과값 타입 존재

    // 성공과 실패 케이스 마다 만드는 번거로움 없애기 이휘 static 함수 정의
    // 에러인 경우 사용하는 메소드
    public static <T> Response<Void> error(String errorCode) {
        return new Response<>(errorCode, null);  // 에러일 경우 result 없이 코드만 따라서 Response<Void>
    }

    //성공일 경우 사용하는 메소드
    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);  // 성공일 경우 result가 각가 달라 <T> 타입 리턴
    }
}
