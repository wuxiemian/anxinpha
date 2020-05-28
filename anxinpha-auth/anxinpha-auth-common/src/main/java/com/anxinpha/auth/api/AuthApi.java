package com.anxinpha.auth.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 尹硕硕
 * @description
 **/
public interface AuthApi {
    @GetMapping("chatLogin")
    Long getUserIdByToken(@RequestParam("token")String token);
}
