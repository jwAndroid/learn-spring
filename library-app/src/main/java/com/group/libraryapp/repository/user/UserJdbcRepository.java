package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(UserCreateRequest dto) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, dto.getName() , dto.getAge());
    }

    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new UserResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                )
        );
    }

    public void updateUserName(UserUpdateRequest dto) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName() , dto.getId());
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql , name);
    }

    public boolean isUserNotExistByName(String name) {
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql , (re, rowNum) -> 0 , name).isEmpty();
    }

    public boolean isUserNotExistById(long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql , (re, rowNum) -> 0 , id).isEmpty();
    }

}
