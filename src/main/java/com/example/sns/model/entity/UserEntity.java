package com.example.sns.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class UserEntity {

    @Id   // 자동 increase
    private Integer id;

    @Column(name = "user_name")
    private String userName;
    private String password;
}
