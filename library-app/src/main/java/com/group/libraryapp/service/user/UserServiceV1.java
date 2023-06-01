package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
       this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest dto) {
        userJdbcRepository.saveUser(dto);
    }

    public List<UserResponse> getUsers() {
       return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest dto) {
        if (userJdbcRepository.isUserNotExistById(dto.getId())) throw new IllegalArgumentException();
        userJdbcRepository.updateUserName(dto);
    }

    public void deleteUser(String name) {
        if (userJdbcRepository.isUserNotExistByName(name)) throw new IllegalArgumentException();
        userJdbcRepository.deleteUser(name);
    }
}
