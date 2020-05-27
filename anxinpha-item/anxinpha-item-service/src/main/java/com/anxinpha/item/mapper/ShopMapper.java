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
}
