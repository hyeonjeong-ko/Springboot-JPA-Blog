package com.example.blog.repository;

import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean등록이 된다 (@Repository 생략 가능)
public interface UserRepository extends JpaRepository<User,Integer> { //관리하는 테이블,해당테이블 PK

}
