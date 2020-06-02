package com.anxinpha.user.service.impl;

import com.anxinpha.auth.utils.JwtUtils;
import com.anxinpha.common.utils.CreateVerifyCode;
import com.anxinpha.user.mapper.UserMapper;
import com.anxinpha.user.pojo.User;
import com.anxinpha.user.service.UserService;
import com.anxinpha.user.utils.CodecUtils;
import com.anxinpha.user.utils.OrderIdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "user:captcha:";



    /**
     * 检测是否存在，type 1用户名 2手机号 3邮箱
     *
     * @param type
     * @param data
     * @return
     */
    @Override
    public Boolean checkUser(Integer type, String data) {
        if (StringUtils.isBlank(data)){
            return null;
        }
        User user = new User();
        if (type==1){
            user.setUsername(data);
        }else if (type==2){
            user.setPhone(data);
        }else if (type==3){
            user.setEmail(data);
        }
        return this.userMapper.selectCount(user) == 0;
    }

    @Override
    public Integer register(User user, String uuid, String code) {
        //1
        if (StringUtils.isBlank(uuid)){
            return null;
        }
        //2.判断验证码
        String result = this.redisTemplate.opsForValue().get(KEY_PREFIX + uuid);
        if (!code.toLowerCase().equals(result)){
            return 1;
        }
        //3.加盐加密
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
        //4.插入
        user.setState(1);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        this.userMapper.insertSelective(user);
        User record = new User();
        record.setUsername(user.getUsername());
        User user1 = this.userMapper.selectOne(record);
        this.userMapper.insertChat(String.valueOf(user1.getId()),user1.getUsername(),user1.getFile());
        this.userMapper.insertFriend(String.valueOf(user1.getId()),"5");
        this.userMapper.insertFriend("5",String.valueOf(user1.getId()));
        return 2;
    }

    @Override
    public String initCaptcha() {
        String captchaId = UUID.randomUUID().toString().replace("-","");
        String code = new CreateVerifyCode().randomStr(4);
        // 缓存验证码
        redisTemplate.opsForValue().set(KEY_PREFIX+captchaId, code.toLowerCase(),2L, TimeUnit.MINUTES);
        return captchaId;
    }

    @Override
    public User login(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        String salt = user.getSalt();
        if (user.getPassword().equals(CodecUtils.md5Hex(password,salt))){
            return user;
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return this.userMapper.selectOne(user);
    }

    @Override
    public List<User> getUsers() {
        return this.userMapper.selectAll();
    }

    @Override
    public Integer getCount() {
        return this.userMapper.selectAll().size();
    }

    @Override
    public Boolean update(User user) {
        if (StringUtils.isBlank(user.getSex())){
            user.setSex(null);
        }
        if (StringUtils.isBlank(user.getFile())){
            user.setFile(null);
        }
        this.userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

}
