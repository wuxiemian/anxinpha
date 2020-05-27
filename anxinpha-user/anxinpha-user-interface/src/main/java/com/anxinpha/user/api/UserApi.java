package com.anxinpha.user.api;

import com.anxinpha.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 尹硕硕
 * @description
 **/
public interface UserApi {
    @PostMapping("login")
    public User login(@RequestParam("username")String username, @RequestParam("password")String password);

    @GetMapping("byUsername")
    public User getUserByUsername(@RequestParam("username")String username);
}
