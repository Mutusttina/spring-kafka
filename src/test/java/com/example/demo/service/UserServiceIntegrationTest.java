package com.example.demo.service;


import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    UserServiceImpl userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    public void testUserService() {

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(new User(1,"Sagar"),new User(2,"Sagar"),new User(3,"Sagar")));

        List<User> users=userService.getAllUsers();
        assertEquals(3,users.size());
    }


}
