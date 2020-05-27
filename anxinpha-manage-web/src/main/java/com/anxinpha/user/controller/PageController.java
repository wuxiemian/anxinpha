package com.anxinpha.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 尹硕硕
 * @description 控制访问各个页面
 **/
@Controller
public class PageController {
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

}
