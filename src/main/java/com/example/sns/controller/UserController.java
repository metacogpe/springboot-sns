package com.example.sns.controller;

import com.example.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor  // UserService를 받아오면서 초기화
public class UserController {

    private final UserService userService; // UserService 불러오기
    // todo : implement
    @PostMapping
    public void join() {
        // join
        userService.join();   // 불러온 userService의 join() 사용 가능
    }
}
