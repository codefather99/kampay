package com.example.kampay.service.impl;

import com.example.kampay.dto.UserDto;
import com.example.kampay.exception.ResourceNotFoundException;
import com.example.kampay.mapper.UserMapper;
import com.example.kampay.model.User;
import com.example.kampay.repository.UserRepository;
import com.example.kampay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
   public UserDto registerUser (UserDto userDto){
        User user = userMapper.mapToUserModel(userDto);

        User registeredUser = userRepository.save(user);

        return userMapper.mapToUserDto(registeredUser);
    }

    @Transactional
    @Override
    public UserDto getUserById(Long userId)
    {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " does not exist"));

       return userMapper.mapToUserDto(foundUser);
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
       List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        // use userId to find id in database
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " does not exist"));

        // if user found, replace existing user record with updated one
        if(userDto.getFirstName() != null)
        {
            existingUser.setFirstName(userDto.getFirstName());
        }

        if(userDto.getLastName() != null)
        {
            existingUser.setLastName(userDto.getLastName());
        }

        if(userDto.getEmail() != null)
        {
            existingUser.setEmail(userDto.getEmail());
        }


        User savedUser = userRepository.save(existingUser);

        return userMapper.mapToUserDto(savedUser);

    }

    @Transactional
    @Override
    public void deleteUser(Long userId)
    {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " does not exist"));

        userRepository.delete(foundUser);

    }


}
