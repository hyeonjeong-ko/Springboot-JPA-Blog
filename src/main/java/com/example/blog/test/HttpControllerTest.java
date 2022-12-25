package com.example.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    // 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.

    @GetMapping("/http/get")
    public String getTest(Member m){ // ..get/?id=1&username=hj&pass..
        return "get 요청" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
    }

    /*
    public String getTest(@RequestParam int id){
        return "get 요청" + m.getId();
    }
    */

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){ // Body에 붙여 보냄(MessageConverter)
        return "post 요청 with json" +  m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
    }

    /*
    public String getTest(@RequestBody String text){
        return "post 요청을" + text + "의 Body 에 받는다";
    }
    */

    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    @DeleteMapping("http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
