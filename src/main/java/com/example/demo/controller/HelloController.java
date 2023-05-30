package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.engine.IterationStatusVar;




@Controller
public class HelloController {
//    http://localhost:8080/hello로 요청시 hello메서드에서 처리
//    http는 국제 통신프로토콜이다.  https(s-secure)보안이 강화된 통신 프로토콜
//    port란 한 IP내에 여러프로그램을 구분짓는 단위. 집주소가 IP, 각집의 방문이 port
//    data만을 return할때는 ResponseBody를 사용한다.
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world--";
    }
//  jsp/thymeleaf같은 템플릿엔진을 사용하여 화면을 return 할때에는 responsebody를 사용하면 안된다.
//  그리고 Model이라는 객체에 data를 담아 return xxx를 하여 xxx.html파일로 데이터를 보낸다.
    @GetMapping("hello-thymeleaf")
    public String hello2(Model model){
        model.addAttribute("getdata", "hello2 world");
        return "hello-th";
    }
//    데이터를 첨부시키지 않고, 화면만을 렌더링(준다)할 수도 있다.
    @GetMapping("hello-html")
    public String hellohtml(){

        return "hello-get-req";
    }

    //    데이터를 첨부시키지 않고, 화면만을 렌더링(준다)할 수도 있다.
    //    http://localhost:8080/hello-param?id=aa
    @GetMapping("hello-param")
    @ResponseBody
    public String helloParam(@RequestParam(value = "id")String id){//throws AccessDeniedException
//        if(true){
//            throw new AccessDeniedException("권한이 없습니다");
//        }
        System.out.println(id);
        return "ok";
    }
    @GetMapping("hello-get-form-req")
    public String helloGetFormReq(){

        return "hello-post-form-req";

    }

//   html의 form형식으로 post요청
//   form형식의 parameter로 데이터가 넘어오므로, RequestParam으로 받아줘야한다.
    @PostMapping("hello-post-form-req")
    @ResponseBody
    public String helloPostFormReq(@RequestParam(value = "name")String myname,
                                 @RequestParam(value = "email")String myemail,
                                 @RequestParam(value = "password")String mypassword){
        System.out.println("이름 : "+myname);
        System.out.println("이메일 : "+myemail);
        System.out.println("비밀번호 : "+mypassword);
        return "ok";
    }

//    테스트 할때에, http://localhost:8080/hello-post-form-req?name=홍길동&email=he@naver.com&password=1234
    @GetMapping("hello-parameter")
    @ResponseBody
    public String helloParameter(@RequestParam(value = "name")String myname,
                                 @RequestParam(value = "email")String myemail,
                                 @RequestParam(value = "password")String mypassword){
        System.out.println("이름 : "+myname);
        System.out.println("이메일 : "+myemail);
        System.out.println("비밀번호 : "+mypassword);
        return "ok";
    }
//  json으로 post를 요청하는 것
    @GetMapping("hello-get-json-req")
    public String helloGetJsonReq(){return "hello-post-json-req";}


//    사용자가 서버로 데이터를 보내는 방식에는 총3가지가 있다.
//    1.?를 통해 parameter 값을 넣어 보내는 방식 : 대부분 get요청시 사용
//    2.form 태그 안에 data를 넣어 보내는 방식: post요청시 사용
//        (보안이 강화되고, URL에 데이터 찍히지 않는다. 그런데, 내부적으로는  ? key1=value1&&key2=value2의 형식을 취한다. )
//    3.json 데이터 형식으로 서버로 보내는 방식 : post요청시 사용
//    json데이터란("key1":"value1", "key2":"value2" } 형식을 취하는 데이터다.
//    현대적인 web서비스에서 대부분의 데이터를 주고 받을때 json을 사용한다.
//    json html의 form태그에 넣어서 보내는 방식이 아니다 보니, Ajax, react 이런 javascript 프레임워크를 사용하게 된다.

    
//    json으로 POST요청이 들이 들어왔을때 DATA를 꺼내기 위해 RequestBody 사용
//    @RequestBody가 붙어있으면 json이고, 이게 없으면 form방식이다.
    @PostMapping("hello-json")
    @ResponseBody
    public String helloJson(@RequestBody  Hello hello,Model model){
        System.out.println("이름 : "+hello.getName());
        System.out.println("이메일 : "+hello.getEmail());

        System.out.println("비밀번호 : "+hello.getPassword());
        model.addAttribute("hello", hello);
        return "result";
    }


//    @RequestBody어노테이션이 붙어 있고, RETURN타입이 객체이면 spring이 json형태로 변환해준다.
    @PostMapping("Good-json-resibse")
    @ResponseBody
    public GoodBye helloJsonResponse(@RequestBody  Hello hello){
        System.out.println("이름 : "+hello.getName());
        System.out.println("이메일 : "+hello.getEmail());
        System.out.println("비밀번호 : "+hello.getPassword());
        GoodBye goodBye1 = new GoodBye();
        goodBye1.setName(hello.getName());
        goodBye1.setEmail(hello.getEmail());
        goodBye1.setComments("Thank You");

        return goodBye1;
    }




}


