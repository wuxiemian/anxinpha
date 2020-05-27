package com.anxinpha.auth.service.impl;

import com.anxinpha.auth.client.UserClient;
import com.anxinpha.auth.config.JwtProperties;
import com.anxinpha.auth.pojo.UserInfo;
import com.anxinpha.auth.service.AuthService;
import com.anxinpha.auth.utils.JwtUtils;
import com.anxinpha.user.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserClient userClient;

    @Override
    public User login(String username, String password) {
        User user = userClient.login(username, password);
        if (user==null){
            return null;
        }
        return user;
    }

    @Override
    public User getUserByUserName(String username) {
        User record = new User();
        record.setUsername(username);
        return this.userClient.getUserByUsername(username);
    }
}
