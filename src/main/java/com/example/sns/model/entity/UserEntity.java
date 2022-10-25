package com.example.sns.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table
public class UserEntity {

    @Id   // 자동 increase
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "registered_at")
    private Timestamp registeredAt;  // 생성시간 : 데이터 관리 용이성 위한 용도

    @Column(name = "updated_at")
    private Timestamp updatedAt;     // 업데이트시간

    @Column(name = "deleted_at")
    private Timestamp deletedAt;    // 삭제시간
}
