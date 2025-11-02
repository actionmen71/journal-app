package com.krishna.demo.controller;


import com.krishna.demo.entity.User;
import com.krishna.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    //earlier used service method saveUser() and now mapped to saveNewUser() to save encrypted password
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user ){
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}


/*So UserController should be accessible only when the user is authenticated, but creating user should be possible
* for public without authentication, so the approach would be put all the controllers which should not authenticated
* in separate Controller class named public, hence, crateUser() is in this class*/