package com.anxinpha.item.mapper;

import com.anxinpha.item.pojo.Shop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface ShopMapper extends Mapper<Shop> {

    @Select("SELECT shop_name FROM tb_shop WHERE id = #{id}")
    String getShopName(@Param("id") Long shopId);

    @Select("UPDATE tb_user SET is_shop = 1 WHERE id = #{id}")
    void setUser(@Param("id") Long userId);
    @Select("SELECT id FROM tb_shop WHERE user_id = #{id}")
    Long getShopIdByUserId(@Param("id") Long userId);
}
