package com.anxinpha.user.controller;

import com.anxinpha.user.pojo.User;
import com.anxinpha.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestParam("username")String username, @RequestParam("password")String password){
        User user = this.userService.login(username,password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("byUsername")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username")String username){
        User user = this.userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("checkUser/{type}")
    public ResponseEntity<Boolean> checkUser(@PathVariable("type")Integer type, @RequestParam("data")String data){
        Boolean result = this.userService.checkUser(type,data);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("register")
    public ResponseEntity<Integer> register(@Valid User user, @RequestParam("uuid") String uuid, @RequestParam("code") String code){
        Integer result = this.userService.register(user,uuid,code);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
