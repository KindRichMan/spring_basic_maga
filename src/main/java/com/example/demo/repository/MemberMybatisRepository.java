package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// mybatis라는 DB Connect기술을 쓰기 이전에는 ibatis라는게 존재했었기에 , Mapper어노테이션은 ibatis 패키지안에 들어있다.
@Mapper
@Repository
public interface MemberMybatisRepository {
//    JPA와는 다르게, 사용하고자 하는 메서드를 사전에 정의해야 한다.
    void save(Member member);
    List<Member> findAll();
    Member findById(Long id);
//    findAll, findById


}
