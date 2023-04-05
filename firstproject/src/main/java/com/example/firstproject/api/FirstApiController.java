package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// 원래 그냥 @Controller 를 썻다. 얘는 뷰페이지를 반환해주는데
// api 는 이렇게 rest controller 를 쓴다. json 을 반환해준다.
// @RestController 와 @Controller 의 차이:
// @Controller 는 @GetMapping("/api/hello") 이런식으로 브라우저에서 요청을 할때 페이지가 응답이였지만
//  실제로 @Controller 의 @GetMapping("/api/hello") 으로 요청을 해보면 응답값이 html 파일이 날라온다.
// @RestController 는 json (데이터) 이 반환된다.
@RestController
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}
