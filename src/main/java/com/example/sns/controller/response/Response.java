package com.example.sns.controller.response;

public class Response<T> {

    private String resultcode;
    private T result; // 결과값 타입이 다를 수 있어 제네릭으로 선언 : UserJoinResponse 타입, LoginResponse 타입 등 다양한 결과값 타입 존재
}
