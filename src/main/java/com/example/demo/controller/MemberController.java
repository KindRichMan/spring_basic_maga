package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberController {
//    MemberService를 주입
    @Autowired
   private MemberService memberService;


//////////////////////////////////////////////////////////////////////////////
//    PostMapping를 통해 MemberService호출하는 Method 생성
//@PostMapping("members")
//@ResponseBody
//public String memberCreate(@RequestParam Member member){
////   Member객체를 만들어서 MemberService 매개변수로 전달
//    Member member1 = new Member();
//
//    member1.setName(member.getName());
//    member1.setEmail(member.getEmail());
//    member1.setPassword(member.getPassword());
//    memberService.create(member1);
//    return "ok";
//}

//    회원등록 form 접근
    @GetMapping("members/new")
    public String memberCreateForm(){
        return "member/member-register";
    }

//    회원 form에서 입력 후 DB저장
    @PostMapping("members/new")
    //@ResponseBody  // input 값을 form-data로 받는 형석
    public String memberCreate(@RequestParam(value = "name")String myname,
                               @RequestParam(value = "email")String myemail,
                               @RequestParam(value = "password")String mypassword) throws SQLException {

        Member member = new Member();

        member.setName(myname);
        member.setEmail(myemail);
        member.setPassword(mypassword);
//      Member객체를 만들어서 MemberService 매개변수로 전달

//        Member는 class를 new하여 객체를 만드는 반면에
//        MemberService는 객체를 만들지 않고, 바로 사용하고 있다.
//        이는 MemberService는 Component를 통해 싱글톤으로 만들어져 있기 때문
//        싱글톤으로 만들어진 Component는 객체를 생성하는것이 아니라 주인(DI)를 받아 사용
        memberService.create(member);
        return "redirect:/";
}

     @GetMapping("members")
    public String memberFindAll(Model model){
         List<Member> members = memberService.findAll();
         model.addAttribute("membersList1" ,members);
        return "member/member-list";
     }

     @GetMapping("member")
    public String memberFindById(Model model,@RequestParam(value = "id") Long myId){
      Member member1 = memberService.findById(myId);
      model.addAttribute("member",member1);
        return "member/member-detail";
     }

     @GetMapping("/")
    public String memberHome(){

        return "member/member-Home";
     }

     @PostMapping("member/update")
//     @ResponseBody
     public String updateMember(    @RequestParam(value = "id")String myId,
                                    @RequestParam(value = "name")String myName,
                                    @RequestParam(value = "email")String myEmail,
                                    @RequestParam(value = "password")String myPassword ) throws Exception
      {

         System.out.println("여기로진입되나요?확인");
             Member member = new Member();
             member.setId(Long.parseLong(myId));
             member.setName(myName);
             member.setEmail(myEmail);
             member.setPassword(myPassword);

             memberService.update(member);
             return "redirect:/";


         }

    @PostMapping("member/delete")
    public String deleteMember(@RequestParam(value = "id") String memberId) throws Exception {
        System.out.println("삭제 :여기로 진입되나요? 확인");

        // Retrieve the member by ID
        Member member = memberService.getMemberById(Long.parseLong(memberId));

        // If the member doesn't exist, throw an exception
        if (member == null) {
            throw new Exception("Member not found");
        }

        // Delete the member
        memberService.delete(member);

        return "redirect:/";
    }




}

