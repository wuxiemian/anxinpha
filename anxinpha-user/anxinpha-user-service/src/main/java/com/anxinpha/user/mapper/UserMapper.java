package com.anxinpha.user.mapper;

import com.anxinpha.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 尹硕硕
 * @description
 **/
public interface UserMapper extends Mapper<User> {
    @Select("INSERT INTO userinfo (userid,nickname,uimg) VALUES (#{userid},#{name},#{img})")
    void insertChat(@Param("userid") String userid, @Param("name") String username, @Param("img") String file);
    @Select("INSERT INTO chat_friends (userid,fuserid,addtime) VALUES (#{userid},#{id},NOW())")
    void insertFriend(@Param("userid") String userid,@Param("id")String id);
}
