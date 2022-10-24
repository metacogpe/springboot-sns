package com.example.sns.fixture;

import com.example.sns.model.entity.UserEntity;
// 테스트용 엔터티 'Fixture' : 변경되지 않는 상태나 데이터를 미리 만들어 두는 작업
public class UserEntityFixture {

    public static UserEntity get(String userName, String password) {
        UserEntity result = new UserEntity();
        result.setId(1);
        result.setUserName(userName);
        result.setPassword(password);

        return result;
    }
}
