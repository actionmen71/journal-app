package com.krishna.demo.controller;


import com.krishna.demo.api.response.WeatherResponse;
import com.krishna.demo.entity.User;
import com.krishna.demo.service.UserService;
import com.krishna.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;


    /*      Earlier when we were not using security context to get username.
    With authorization, username and password from postman request are stored in security context
    and that mean that user details has been verified, no need of path variables.
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable String username) */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDB = userService.findByUserName(username);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(userInDB,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        userService.deleteByUserName(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        WeatherResponse city_weather = weatherService.getWeather("Vadodara");
        String greeting ="";
        if(city_weather != null) {
            greeting=" Weather feels like " + city_weather.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Welcome "+name + greeting, HttpStatus.OK);
    }

}


/*getting all users functionality should be available to admin and not user, so comment it.*/
//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        List<User> all = userService.getAll();
//        if (all != null && !all.isEmpty()) {
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }