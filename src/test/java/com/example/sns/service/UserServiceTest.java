package com.example.sns.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;  // 테스트 위해 UserService 가져오기

    @Test
    void 회원가입이_정상적으로_동작하는_경우() {
        String userName = "userName";
        String password = "password";

        // mocking : 회원가입 정상 여부 목킹 필요 -> UserService.java 에서 좀 더 구현 필요
        Assertions.assertDoesNotThrow(()-> userService.join(userName, password));
    }
}
