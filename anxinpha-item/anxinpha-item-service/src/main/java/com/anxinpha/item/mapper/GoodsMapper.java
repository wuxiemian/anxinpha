package com.anxinpha.item.mapper;

import com.anxinpha.item.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 尹硕硕
 * @description 商品Mapper
 **/
public interface GoodsMapper extends Mapper<Goods>, SelectByIdListMapper<Goods,Long> {

    @Select("UPDATE tb_goods SET `status` = 0 WHERE id = #{id}")
    void stopGoods(@Param("id") Long id);
    @Select("UPDATE tb_goods SET `status` = 1 WHERE id = #{id}")
    void startGoods(@Param("id") Long id);
    @Select("SELECT id FROM tb_shop WHERE user_id = #{id}")
    Long getShopIdByUserId(@Param("id") Long userId);
}
