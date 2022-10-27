package com.example.sns.controller;

import com.example.sns.controller.request.UserJoinRequest;
import com.example.sns.model.User;
import com.example.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor  // UserService를 받아오면서 초기화
public class UserController {

    private final UserService userService; // UserService 불러오기
    // todo : implement
    @PostMapping("/join")
    // UserJoinRequest 클래스에 정의된 userName 과 password 를 RequestBody 로 불러옴
    public void join(@RequestBody UserJoinRequest request) {
        // join
        User user = userService.join(request.getUserName(), request.getPassword()); // UserJoinRequest 로 불러온 userName 과 password 를 userService 로 넘김
    }
}
