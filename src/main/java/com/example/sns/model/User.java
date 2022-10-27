package com.example.sns.model;

import com.example.sns.model.entity.UserEntity;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@AllArgsConstructor
public class User {

    private Integer id;
    private String userName;
    private String password;
    private UserRole userRole;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    // 엔터티를 DTO 로 변환
    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole(),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }
}
