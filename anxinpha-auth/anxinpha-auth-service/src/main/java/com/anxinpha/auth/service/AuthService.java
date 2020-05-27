package com.anxinpha.auth.service;

import com.anxinpha.auth.pojo.UserInfo;
import com.anxinpha.user.pojo.User;

/**
 * @author 尹硕硕
 * @description
 **/
public interface AuthService {

    User login(String username, String password);

    User getUserByUserName(String username);
}
