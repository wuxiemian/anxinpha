package com.anxinpha.user.mapper;

import com.anxinpha.user.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 尹硕硕
 * @description
 **/
public interface OrderMapper extends Mapper<Order> {
    @Select("SELECT id FROM tb_shop WHERE user_id = #{id}")
    Long getShopIdByUserId(@Param("id") Long userId);
}
