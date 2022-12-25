package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // user 클래스가 MySQL에 자동으로 테이블 생성이 됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스,auto_increment

    @Column(nullable = false,length=200)
    private String content;

    @ManyToOne //Many(reply) to one(board)
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne //Many(reply) to one(user)
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

}
