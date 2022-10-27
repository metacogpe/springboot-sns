package com.example.sns.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATED \"user\" SET deleted_at = NOW() where id=?")  // delete sql 시 삭제시간 업데이트
@Where(clause = "deleted_at is NULL")  // 삭제가 안된 것만 가져오도록 구현
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

    @PrePersist  // registerdAt 매번 입력의 불편함 해소 : 저장 되기 전에 현재 시각 저장
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate  // 업데이트전에도 업데이트 시각 저장
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }
}
