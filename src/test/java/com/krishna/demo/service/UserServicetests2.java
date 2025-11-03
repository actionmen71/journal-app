package com.krishna.demo.service;

import com.krishna.demo.entity.User;
import com.krishna.demo.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServicetests2 {

    @Autowired
    private UserRepository userRepository;


    @ParameterizedTest
    @ValueSource(strings={"Ram","moonshot","Sita","Admin","payal"})
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for "+name);

    }

}
