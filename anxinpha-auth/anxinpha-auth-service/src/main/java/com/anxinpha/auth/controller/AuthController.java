package com.anxinpha.auth.controller;

import com.anxinpha.auth.config.JwtProperties;
import com.anxinpha.auth.pojo.UserInfo;
import com.anxinpha.auth.service.AuthService;
import com.anxinpha.auth.utils.JwtUtils;
import com.anxinpha.common.utils.CookieUtils;
import com.anxinpha.user.pojo.User;
import com.netflix.client.http.HttpResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("checkLogin")
    public ResponseEntity<User> checkLogin(
            @CookieValue(value = "AX_TOKEN",required = false) String token,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse){
        if (StringUtils.isBlank(token)){
            return ResponseEntity.ok(null);
        }
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(token,jwtProperties.getPublicKey());
            if (userInfo==null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            token = JwtUtils.generateToken(userInfo,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            CookieUtils.setCookie(httpServletRequest,httpServletResponse,jwtProperties.getTokenName(), token,jwtProperties.getExpire()*60);
            User user = this.authService.getUserByUserName(userInfo.getUsername());
            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("login")
    public ResponseEntity<User> login(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse){
        try {
            User user = this.authService.login(username,password);
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(user.getUsername());
            userInfo.setId(user.getId());
            String token = JwtUtils.generateToken(userInfo,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            CookieUtils.setCookie(httpServletRequest,httpServletResponse,jwtProperties.getTokenName(), token,jwtProperties.getExpire()*60);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("chatLogin")
    public ResponseEntity<Long> getUserIdByToken(@RequestParam("token")String token){
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            return ResponseEntity.ok(userInfo.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
