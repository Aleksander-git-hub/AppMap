package com.project.webapp.controller;

import com.project.webapp.dto.UserDto;
import com.project.webapp.entity.User;
import com.project.webapp.mapper.UserMapper;
import com.project.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/user")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.saveUser(userDto));
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @GetMapping(value = "/users")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> usersDto = new ArrayList<>();
        users.stream()
                .forEach(user -> usersDto.add(userMapper.toDto(user)));
        return usersDto;
    }

    @DeleteMapping(value = "/user/{id}")
    public UserDto deleteUser
            (@PathVariable(value = "id") Long id) {
        return userMapper.toDto(userService.deleteUserById(id));
    }

    @PutMapping(value = "/user/{id}")
    public UserDto updateUserById
            (@RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
        return userMapper.toDto(userService.updateUserById(userDto, id));
    }
}
