package com.krishna.demo.service;


import com.krishna.demo.entity.User;
import com.krishna.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //this is normal method to save username and password without encryption
    //this method should be used for saving or updating username and password without encryption
    public void saveUser(User user){
        userRepository.save(user);
    }


    //this method to save new user with encrypted password and roles
    //make sure to change the method name where the previous method was used
    //this is for saving new user and not updating username and password (otherwise this method will re-encode the encoded password)
    public void saveNewUser(User user){
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveNewAdmin(User user){
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
    public void deleteByUserName(String username){
        userRepository.deleteByUserName(username);
    }

}


/*used earlier*/
//    public Optional<User> findById(ObjectId id){
//        return userRepository.findById(id);
//    }
