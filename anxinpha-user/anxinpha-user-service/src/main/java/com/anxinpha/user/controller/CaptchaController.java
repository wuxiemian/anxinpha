package com.anxinpha.user.controller;

import com.anxinpha.common.utils.CreateVerifyCode;
import com.anxinpha.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("captcha")
public class CaptchaController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    private static final String KEY_PREFIX = "user:captcha:";

    @GetMapping(value = "/init")
    public ResponseEntity<String> initCaptcha() {
        return ResponseEntity.ok(this.userService.initCaptcha());

    }

    @RequestMapping(value = "/draw/{captchaId}", method = RequestMethod.GET)
    public void drawCaptcha(@PathVariable("captchaId") String captchaId, HttpServletResponse response) throws IOException {

        //得到验证码 生成指定验证码
        String code=redisTemplate.opsForValue().get(KEY_PREFIX+captchaId);
        CreateVerifyCode vCode = new CreateVerifyCode(116,36,4,10,code);
        response.setContentType("image/png");
        vCode.write(response.getOutputStream());
    }
}
