package com.anxinpha.user.service;

import com.anxinpha.user.pojo.User;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface UserService {
    /**
     * 检测是否存在，type 1用户名 2手机号 3邮箱
     * @param type
     * @param data
     * @return
     */
    Boolean checkUser(Integer type, String data);


    Integer register(User user, String uuid, String code);

    String initCaptcha();

    User login(String username, String password);

    User getUserByUsername(String username);

    List<User> getUsers();

    Integer getCount();

    Boolean update(User user);
}
