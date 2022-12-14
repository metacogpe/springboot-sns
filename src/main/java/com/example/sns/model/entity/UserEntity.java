package com.example.sns.model.entity;

import com.example.sns.model.UserRole;
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
@Table(name = "\"user\"") // PostgreSQL에 존재하는 기본 default user table 과 구분하기 위해 double quotation 추가
// 삭제 시간 업데이트를 위해 어노테이션을 아래와 같이 구성
@SQLDelete(sql = "UPDATED \"user\" SET deleted_at = NOW() where id=?")  // delete sql 시 삭제시간 업데이트
@Where(clause = "deleted_at is NULL")  // 삭제가 안된 것만 가져오도록 구현
public class UserEntity {

    @Id   // 자동 increase
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB가 제공하는 시퀀스를 사용
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)  // enum 사용 위한 어노테이션
    private UserRole role = UserRole.USER;  // enum 클래스 적용

    @Column(name = "registered_at")
    private Timestamp registeredAt;  // 생성시간 : 데이터 관리 용이성 위한 용도

    @Column(name = "updated_at")
    private Timestamp updatedAt;     // 업데이트시간

    @Column(name = "deleted_at")
    private Timestamp deletedAt;    // 삭제시간

    @PrePersist  // registeredAt 매번 입력의 불편함 해소 : 저장 되기 전에 현재 시각 저장
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate  // 업데이트전에도 업데이트 시각 저장
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    // 유저엔터티를 반환하는 메소스 생성
    //  - User 와 UserEntity 클래스를 별도로 분리 설계
    //  - User : DTO 이며, 서비스 처리를 위해 사용( DB 의 변경에 영향을 주지 않는 구조)
    //  - UserEntity : DB에 저장할 때에만 엔터티 사용
    public static UserEntity of(String userName, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        return userEntity;  // 주어진 userName/password 로 userEntity 생성 리턴
    }
}
