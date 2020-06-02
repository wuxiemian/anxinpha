package com.anxinpha.item.service;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.item.pojo.Shop;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface ShopService {
    /**
     * 获取所有的店铺集合
     * @return
     */
    List<Shop> getAllShops(String searchKey, String minDate, String maxDate);

    /**
     * 获取店铺数量
     * @return
     */
    int getShopCount();

    /**
     * 添加店铺
     * @return
     */
    void addShop(Shop shop);

    /**
     * 修改店铺信息
     * @return
     */
    void updateShop(Shop shop);

    /**
     * 删除店铺及其商品
     * @return
     */
    void deleteShop(Long id);

    /**
     * 通过名获得店铺
     * @param shopName
     * @return
     */
    Shop getShopByShopName(String shopName);

    Shop getInfo(Long userId);

    String getShopName(Long shopId);

    Long getShopId(Long userId);
}
