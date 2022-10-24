package com.example.sns.service;

import com.example.sns.exception.SnsApplicationException;
import com.example.sns.model.User;
import com.example.sns.model.entity.UserEntity;
import com.example.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    // todo : implement
    public User join(String userName, String password) {
        // 회원 가입에는 2가지가 필요 : 체크 후에 등록
        // 1) 체크 : 회원가입하려는 userName 으로 회원가입된 user 가 있는지
        Optional<UserEntity> userEntity = userEntityRepository.findByUsername(userName);  //정의한 findByUsername()함수 사용
        // 2) 등록 : 회원가입 진행 = user 를 등록
        userEntityRepository.save(new UserEntity());  // 생성자 실행하여 객체 만들기 : new UserEntity()
        return new User();
    }

    // login 의 경우, jwt 사용하므로 암호화된 문자열인 String 으로 리턴
    // todo : implement
    public String login(String userName, String password) {
        // 회원가입 여부 체크 : userName 존재하는 경우 userEntity 로 반환 ( 또는 존재하지 않는 경우 예외처리 )
        UserEntity userEntity = userEntityRepository.findByUsername(userName).orElseThrow(() -> new SnsApplicationException());

        // 비밀번호 체크 : 입력받은 패스워드와 등록된 패스워드가 다를 경우 예외 처리
       
        // 토큰 생성

        return "";
    }
}
