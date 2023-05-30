package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
//    private final List<User> users = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest dto) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        // update: 생성, 수정, 삭제에 쓰인다.
        jdbcTemplate.update(sql, dto.getName() , dto.getAge());
    }

   @GetMapping("/user")
   public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user"; // user 테이블

       /*
       * query 함수의 첫번째 인자는 해당 sql 을, 두번째 인자는 rowMapper 이다.
       * GetMapping 이니까 가져오는거고, query 를 통해 테이블에서 가져온다.
       * resultSet 으로 각 맞는 타입을 받아서 new UserResponse 로 반환한다.
       * */
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");

            return new UserResponse(id, name, age);
        });
   }

   @PatchMapping("/user")
   public void updateUser(@RequestBody UserUpdateRequest dto) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName() , dto.getId());
   }

   @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        /*
        * @RequestParam 는 파라미터 {{baseurl}}/user?name=jw
        * */
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql , name);
   }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        /*
         * @PathVariable 는 주소 옆에다가.
         * */
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql , id);
    }
}
