package com.example.sns.controller;

import com.example.sns.controller.request.UserJoinRequest;
import com.example.sns.controller.request.UserLoginRequest;
import com.example.sns.exception.SnsApplicationException;
import com.example.sns.model.User;
import com.example.sns.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc   // api test를 위한 설정
public class UserControllerTest {

    @Autowired  // MockMvc을 가져오기
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; //오브젝트 맵퍼로 UserJoinRequest(body내용)을 가져옴

    @MockBean
    private UserService userService; // 회원가입(join())테스트를 위해 UserService 가져오기, 아래의 mocking 에서 사용

    @Test
    public void 회원가입() throws Exception {
        String userName = "userName";
        String password = "password";

        // todo : mocking
        // 회원가입시 User 클래스 반환 : User model 정의 필요
        when(userService.join()).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                // todo : add request body (ObjectMapper 사용)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());   // 회원가입 성공의 경우
    }

    @Test
    public void 회원가입_가입된_userName으로_회원가입_하는경우_에러반환() throws Exception {
        String userName = "userName";
        String password = "password";

        // todo : mocking
        when(userService.join()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // todo : add request body
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict());   // 회원이 존재하므로 Conflict 상태 처리
    }

    @Test
    public void 로그인() throws Exception {
        String userName = "userName";
        String password = "password";

        // todo : mocking
        // 회원가입시 User 클래스 반환 : User model 정의 필요
        when(userService.login()).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        // todo : add request body (ObjectMapper 사용)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());   // 회원가입 성공의 경우
    }

    @Test
    public void 로그인시_미가입_userName_입력경우_에러반환() throws Exception {
        String userName = "userName";
        String password = "password";

        // todo : mocking
        // 회원가입시 User 클래스 반환 : User model 정의 필요
        when(userService.login()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        // todo : add request body (ObjectMapper 사용)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());   // 미가입 회원
    }

    @Test
    public void 로그인시_틀린_password_입력경우_에러반환() throws Exception {
        String userName = "userName";
        String password = "password";

        // todo : mocking
        // 회원가입시 User 클래스 반환 : User model 정의 필요
        when(userService.login()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        // todo : add request body (ObjectMapper 사용)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized());   // 인증 실패 : 패스워드 오류
    }

}
