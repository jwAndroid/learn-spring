package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

   private final UserServiceV1 userServiceV1;

   public UserController(UserServiceV1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

   @PostMapping("/user")
   public void saveUser(@RequestBody UserCreateRequest dto) {
        userServiceV1.saveUser(dto);
    }

   @GetMapping("/user")
   public List<UserResponse> getUsers() {
       return userServiceV1.getUsers();
   }

   @PatchMapping("/user")
   public void updateUser(@RequestBody UserUpdateRequest dto) {
       userServiceV1.updateUser(dto);
   }

   @DeleteMapping("/user")
   public void deleteUser(@RequestParam String name) {
       userServiceV1.deleteUser(name);
   }
}
