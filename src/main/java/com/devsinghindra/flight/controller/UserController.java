package com.devsinghindra.flight.controller;

import com.devsinghindra.flight.model.StringResponse;
import com.devsinghindra.flight.model.User;
import com.devsinghindra.flight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    public User getUser(@RequestParam String aadhaarNo) {
        return userService.getUser(aadhaarNo);
    }

    @PostMapping
    public StringResponse createUser(@RequestBody User user) {
//        User u=User.builder().userAadhaarNo("123").userEmail("abc@gmail.com")
//                .userName("Devendra Singh").userPassword("12345").userPhone("9999999999").build();
        userService.createUser(user);
        return new StringResponse("User Created");
    }

    @PutMapping
    public StringResponse updateUser(@RequestParam String aadhaarNo,@RequestBody User user) {
        userService.updateUser(user);
        return new StringResponse("User Updated");
    }

    @DeleteMapping
    public StringResponse deleteUserById(@RequestParam String aadhaarNo) {
        userService.deleteUserById(aadhaarNo);
        return new StringResponse("User Deleted");
    }

}
