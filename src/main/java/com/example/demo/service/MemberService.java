package com.example.demo.service;

import com.example.demo.repository.MemberJdbcRepository;
import com.example.demo.repository.MemberMybatisRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MemberService {

//    service에서 repository를 주입 받기 위해서, Autowired를 사용
//    SpringData Jpa를 사용한 repository
    @Autowired
    private MemberRepository memberRepository; // JPA 방식

//    SpringData Mybatis를 사용한 repository
//    jpa와 함께 사용할수도 있다. 복잡한 service logic 또는 heavy한 쿼리가 있을경우
//    jpa로는 한계가 있으므로, 현업에서는 mybatis와 jpa를 섞어 사용하기도 한다.
    @Autowired
    private MemberMybatisRepository memberMybatisRepository;  //mybatis 방식

    @Autowired
    private MemberJdbcRepository memberJdbcRepository;

//     회원가입 ---return타입이 필요없다. void
    public void create(Member member) throws SQLException {
//        System.out.println("memberJdbcRepository테스트");
        memberRepository.save(member);
    }


//    회원목록조회
//    memberRepository.findAll()의 기본 return 타입은 List<해당객체>
    public List<Member> findAll(){
//        System.out.println("jdbc로 전체목록조회");
       List<Member> members =  memberRepository.findAll();

       return members;
    }
//    회원조회
    public Member findById(Long id){
        Member member = memberRepository.findById(id).orElse(null);
//        System.out.println("jdbc로 목록 1개조회");
        return member;
    }

//    업데이트
public void update(Member member) throws Exception {
        Member member1 = memberRepository.findById(member.getId()).orElse(null);
        if(member1== null){
            System.out.println("아이디를 전달받지 못했습니다.");
            throw new Exception();
        }else {
            member1.setName(member.getName());
            member1.setEmail(member.getEmail());
            member1.setPassword(member.getPassword());
            memberRepository.save(member1);
        }
//         save는 이미 존재하는 pk(id)이 있으면 update로 동작, id값이 없으면 insert로 동작
               //memberRepository.save(member);
}


    public Member getMemberById(Long memberId) throws Exception {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new Exception("회원을 찾을 수 없습니다."));
    }

    //delete
    public void delete(Member member) {
        memberRepository.delete(member);
    }

}
