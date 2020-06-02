package com.chat.controller;

import com.chat.bean.Login;
import com.chat.client.AuthClient;
import com.chat.service.LoginService;
import com.chat.util.Md5Util;
import com.chat.util.exception.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginCtrl {
    @Autowired
    LoginService loginService;
    @Autowired
    private AuthClient authClient;
    @GetMapping("/")
    public String tologin(@CookieValue(value = "AX_TOKEN",required = false)String token, HttpSession session){
        if (StringUtils.isBlank(token)){
            return "error/error";
        }
        Long id = authClient.getUserIdByToken(token);
        session.setAttribute("userid",String.valueOf(id));
        return "chat/chats";
    }
    /**
     * 登陆
     * */
    @PostMapping("/justlogin")
    @ResponseBody
    public R login(@RequestBody Login login, HttpSession session){
        login.setPassword(Md5Util.StringInMd5(login.getPassword()));
        String userid = loginService.justLogin(login);
        if(userid==null){
            return R.error().message("账号或者密码错误");
        }
        session.setAttribute("userid",userid);
        return R.ok().message("登录成功");
    }
}
