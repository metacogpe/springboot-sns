package com.example.sns.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserEntity {

    @Id   // 자동 increase
    private Integer id;
}
