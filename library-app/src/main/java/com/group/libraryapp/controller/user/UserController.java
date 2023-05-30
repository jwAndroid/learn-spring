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
       * jdbcTemplate.query 구문은 LIST 를 반환한다.
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
        String readSql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql , (re, rowNum) -> 0 , dto.getId()).isEmpty();
        // (re, rowNum) -> 0 은 결과가 있다면, 0을 반환한다. 그렇지 않으면 .isEmpty() 를 타서
       // isUserNotExist 가 true 가 된다.

        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName() , dto.getId());
   }

   @DeleteMapping("/user")
   public void deleteUser(@RequestParam String name) {
       String readSql = "SELECT * FROM user WHERE name = ?";
       boolean isUserNotExist = jdbcTemplate.query(readSql , (re, rowNum) -> 0 , name).isEmpty();

       if (isUserNotExist) {
           throw new IllegalArgumentException();
       }

        //@RequestParam 는 파라미터 {{baseurl}}/user?name=jw
       String sql = "DELETE FROM user WHERE name = ?";
       jdbcTemplate.update(sql , name);
   }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        //@PathVariable 는 주소 옆에다가.
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql , id);
    }

    @GetMapping("/user/error-test")
    public void errorTest() {
        throw new IllegalArgumentException();
    }
}
