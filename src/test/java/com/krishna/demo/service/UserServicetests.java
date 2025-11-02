package com.krishna.demo.service;

import com.krishna.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicetests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd(){
        assertEquals(4,2+2);
        assertEquals(4,2+2);
    }
    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("Ram"));
    }

    @Test
    public void testDeleteByUserName(){
        assertNotNull(userRepository.deleteByUserName("Ram"));
    }
}
