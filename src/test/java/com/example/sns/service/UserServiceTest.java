package com.example.sns.service;

import com.example.sns.exception.SnsApplicationException;
import com.example.sns.fixture.UserEntityFixture;
import com.example.sns.model.entity.UserEntity;
import com.example.sns.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;  // 테스트 위해 UserService 가져오기

    @MockBean
    private UserEntityRepository userEntityRepository; // 회원 여부 목킹 위해 주입 : 아래 mocking 에서 사용

    @Test
    void 회원가입이_정상적으로_동작하는_경우() {
        String userName = "userName";
        String password = "password";

        // mocking : 회원가입 정상 여부 목킹 필요 -> UserService.java 에서 좀 더 구현 필요
        when(userEntityRepository.findByUsername(userName)).thenReturn(Optional.empty());  // 회원정보 없는 상태
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));  // 저장 후 리턴시 UserEntity 목킹을 반환
        // 회원가입 정상 가입일 경우 위의 2가지 경우로 테스트 가능
        Assertions.assertDoesNotThrow(()-> userService.join(userName, password));
    }

    @Test
    void 회원가입시_userName으로_회원가입한_유저가_이미_있는_경우() {
        String userName = "userName";
        String password = "password";

        // mocking
        // 유저가 반환되어야 함 : Optional.of
        when(userEntityRepository.findByUsername(userName)).thenReturn(Optional.of(mock(UserEntity.class)));  // 회원정보 있는 상태
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));  // 저장 후 리턴시 UserEntity 목킹을 반환

        // 조인을 했을 때, exception 반환 필요
        Assertions.assertThrows(SnsApplicationException.class, ()-> userService.join(userName, password));
    }

    @Test
    void 로그인_정상적으로_동작하는_경우() {
        String userName = "userName";
        String password = "password";

        UserEntity fixture = UserEntityFixture.get(userName,password);

        // mocking
        when(userEntityRepository.findByUsername(userName)).thenReturn(Optional.of(fixture));
        Assertions.assertDoesNotThrow(()-> userService.login(userName, password));  // 정상 로그인의 경우 Throw 미발생
    }

}
