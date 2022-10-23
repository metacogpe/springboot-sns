package com.example.sns.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc   // api test를 위한 설정
public class UserControllerTest {

    @Autowired  // MockMvc을 가져오기
    private MockMvc mockMvc;

    @Test
    public void 회원가입() {
        String userName = "userName";
        String password = "password";

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                // todo : add request body
                .content()
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());   // 회원가입 성공의 경우
    }

    @Test
    public void 회원가입_가입된_userName으로_회원가입_하는경우_에러반환() {
        String userName = "userName";
        String password = "password";

        // todo : mocking
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        // todo : add request body
                        .content()
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict());   // 회원이 존재하므로 Conflict 상태 처리
    }
}
