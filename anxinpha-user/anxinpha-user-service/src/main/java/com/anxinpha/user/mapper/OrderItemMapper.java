package com.anxinpha.user.mapper;

import com.anxinpha.user.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 尹硕硕
 * @description
 **/
public interface OrderItemMapper extends Mapper<OrderItem> {
    @Select("SELECT shop_id FROM tb_goods WHERE id =#{goodsId}")
    Long getShopIdByGoodsId(@Param("goodsId") Long productId);
}
