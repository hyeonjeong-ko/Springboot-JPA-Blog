package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity // user 클래스가 MySQL에 자동으로 테이블 생성이 됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content; // 섬머노트 라이브러리

    @ColumnDefault("0")
    private int count; // 조회수

    // DB는 오브젝트를 저장할 수 없다. (FK)
    // JAVA는 오브젝를 저장할 수 있다.
    @ManyToOne(fetch = FetchType.EAGER) //board(many) to one(user) // user한개니까가져옴^^
    @JoinColumn(name="userId")
    private User user;

    // mappedBy 필드명 : 연관관계의 주인이 아니다. (난 FK가아니오! DB에 칼럼 만들지마세요)
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //필요하면 들고옴
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;

}
