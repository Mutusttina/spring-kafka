package com.example.demo.service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository= Mockito.mock(UserRepository.class);
        userService=new UserServiceImpl(userRepository);
    }

    @Test
    void getAllUsers() {
        List<User> mockUsers = Arrays.asList(
                new User(1, "Alice"),
                new User(2, "Bob")
        );

        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);
        List<User> users=userService.getAllUsers();
        assertEquals(mockUsers,users);
        assertEquals(2,users.size());

    }
}