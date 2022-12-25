package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // user 클래스가 MySQL에 자동으로 테이블 생성이 됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert // insert 시에 null인 필드를 제외시켜 준다.
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프토젝트에서 연결된 DB의 넘버링 전략을 따라감
    private int id; // sequence,auto_increment

    @Column(nullable = false,length = 30,unique = true)
    private String username; // id

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    //@ColumnDefault("'user'")
    @Enumerated(EnumType.STRING) // DB는 RoleType이라는 게 없다
    private RoleType role; // Enum을 쓰는게 좋다(도메인 범위설정 가능). (admin/user/manager...)

    @CreationTimestamp //시간이 자동 입력됨
    private Timestamp createDate;
}
