package com.aoya.controller;

import com.aoya.domain.AyUser;
import com.aoya.service.UserService;
import com.aoya.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iext/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public Result userList() {
        return userService.userList();
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody AyUser user) {
        return userService.addUser(user);
    }
}
