package com.example.sns.service;

import com.example.sns.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // todo : implement
    public User join() {
        return new User();
    }

    // login 의 경우, jwt 사용하므로 암호화된 문자열인 String 으로 리턴
    // todo : implement
    public String login() {
        return "";
    }
}
