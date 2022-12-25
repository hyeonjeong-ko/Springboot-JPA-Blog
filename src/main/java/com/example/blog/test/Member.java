package com.example.blog.test;

import lombok.*;

@Data
//@NoArgsConstructor
//@RequiredArgsConstructor // final 붙은 것에 contructor만듦
public class Member {
    private int id;
    private String username;
    private String password;

    private String email;

    // ** @Builder **
    // Member m = Member.builder().username("쩡이").password("1234").email("toyu").build();
    // 생성자 순서 안지켜도됨
    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }



}
