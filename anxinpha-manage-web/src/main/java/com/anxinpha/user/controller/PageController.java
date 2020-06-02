package com.anxinpha.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 尹硕硕
 * @description 控制访问各个页面
 **/
@Controller
public class PageController {
    @RequestMapping("/") public String showIndex(){
        return "index";
    }

    @GetMapping("/{page}")
    public String showPage(@PathVariable String page,HttpServletResponse response){
        return page;
    }

}
