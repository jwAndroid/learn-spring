package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }
    public void saveUser(UserCreateRequest dto) {
        userRepository.saveUser(dto);
    }

    public List<UserResponse> getUsers() {
       return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest dto) {
        if (userRepository.isUserNotExistById(dto.getId())) throw new IllegalArgumentException();
        userRepository.updateUserName(dto);
    }

    public void deleteUser(String name) {
        if (userRepository.isUserNotExistByName(name)) throw new IllegalArgumentException();
        userRepository.deleteUser(name);
    }
}
