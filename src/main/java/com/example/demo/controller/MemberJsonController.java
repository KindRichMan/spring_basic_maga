package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberJsonController {

    @Autowired
    private MemberService memberService;


    //    회원 form에서 입력 후 DB저장
    @PostMapping("json/members/new")
    @ResponseBody  // input 값을 form-data로 받는 형석
    public String memberCreate(@RequestParam(value = "name")String myname,
                               @RequestParam(value = "email")String myemail,
                               @RequestParam(value = "password")String mypassword) throws SQLException {

        Member member = new Member();

        member.setName(myname);
        member.setEmail(myemail);
        member.setPassword(mypassword);
//      Member객체를 만들어서 MemberService 매개변수로 전달

        memberService.create(member);
        return "ok";
    }


    @GetMapping("json/members")
    @ResponseBody
    public List<Member> memberFindAll(){
        List<Member> members = memberService.findAll();

        return members;
    }

    @GetMapping("json/member")
    public Member memberFindById(@RequestParam(value = "id") Long myId){
        Member member = memberService.findById(myId);

        return member;
    }



}
