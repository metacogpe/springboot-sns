package com.example.sns.service;

import com.example.sns.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // todo : implement
    public User join(String userName, String password) {
        // 회원 가입에는 2가지가 필요 : 체크 후에 등록
        // 1) 체크 : 회원가입하려는 userName 으로 회원가입된 user 가 있는지

        // 2) 등록 : 회원가입 진행 = user 를 등록
        return new User();
    }

    // login 의 경우, jwt 사용하므로 암호화된 문자열인 String 으로 리턴
    // todo : implement
    public String login() {
        return "";
    }
}
