package com.project.webapp.service;

import com.project.webapp.dto.UserDto;
import com.project.webapp.entity.User;
import com.project.webapp.exceptions.NotFoundException;
import com.project.webapp.mapper.UserMapper;
import com.project.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(UserDto userDto) {
        return userRepository.save(userMapper.toEntity(userDto));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User deleteUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        existingUser.setDeleted(true);
        return userRepository.save(existingUser);
    }

    public User updateUserById(User user, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setSecondName(user.getSecondName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }
}
