package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        // body 를 객체로 표현할 dto 가 필요하다.

        // 1. /user 요청이 들어오면 UserCreateRequest 에서 매핑시켜서 dto request 가 생성된다.
        // 2. dto request 값을 가지고 users 리스트에 add 시켜준다.
        // 3. 중간에 User 라는 클래스로 체크하여준다.

        users.add(new User(request.getName() , request.getAge()));
    }

   @GetMapping("/user")
   public List<UserResponse> getUsers() {
        // GET 의 DTO 는 보통 response 에 들어가게 된다.
       List<UserResponse> responses = new ArrayList<>();

       for (int i = 0; i < users.size(); i++) {
           responses.add(new UserResponse(i + 1 , users.get(i)));
       }

       return responses;
   }
}
