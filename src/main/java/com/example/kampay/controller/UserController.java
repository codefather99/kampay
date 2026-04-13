package com.example.kampay.controller;

import com.example.kampay.dto.UserDto;
import com.example.kampay.model.User;
import com.example.kampay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Build REST API
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
        UserDto registeredUser = userService.registerUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId)
    {
        UserDto foundUser = userService.getUserById(userId);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> foundUsers = userService.getAllUsers();
        return ResponseEntity.ok(foundUsers);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody  UserDto userDto)
    {
        UserDto updatedUser = userService.updateUser(userId, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser (@PathVariable("id") Long userId)
    {

        userService.deleteUser(userId);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
