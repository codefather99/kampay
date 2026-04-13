package com.example.kampay.service;

import com.example.kampay.dto.UserDto;
import com.example.kampay.model.User;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);
}
