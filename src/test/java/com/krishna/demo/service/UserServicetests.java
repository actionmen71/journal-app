package com.krishna.demo.service;

import com.krishna.demo.entity.User;
import com.krishna.demo.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


//This annotation will run the spring container for debugging.
//It will load all the dependencies which are needed because they are loaded at runtime.

@SpringBootTest
public class UserServicetests {

    @Autowired
    private UserRepository userRepository;

    //simple test
    @Test
    public void testAdd(){
        assertEquals(4,2+2);
        assertEquals(4,2+2);
    }

    //actual tests
    @Disabled
    @ParameterizedTest
    @ValueSource(strings={"Ram","moonshot","Sita","Admin","payal"})
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for "+name);

    }

    @Disabled
    @Test
    public void testJournalEntries(){
        User user = userRepository.findByUserName("moonshot");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

//    @ParameterizedTest
//    @CsvFileSource("C:\\Users\\actio\\Desktop\\journal-app\\MethodTesting.csv")
//    public void testParams(int a, int b, int expected){
//        assertEquals(expected, a+b);
//    }

//    @Test
//    public void testDeleteByUserName(){
//
//        assertTrue(userRepository.deleteByUserName("Ram"));
//    }
}
