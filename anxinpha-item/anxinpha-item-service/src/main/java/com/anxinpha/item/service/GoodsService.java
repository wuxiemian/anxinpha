package com.anxinpha.item.service;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.item.bo.GoodsBo;
import com.anxinpha.item.bo.GoodsDet;
import com.anxinpha.item.pojo.Goods;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface GoodsService {
    /**
     * 根据店铺id获取该店铺所有商品
     * @param id
     * @return
     */
    List<Goods> getShopGoods(Long id, String searchKey, String minDate, String maxDate);

    /**
     * 获得店铺下商品数量
     * @param id
     * @return
     */
    int getGoodsCount(Long id);

    /**
     * 删除商品
     * @param id
     */
    void deleteGoods(String id);

    /**
     * 新建商品
     * @param goods
     */
    void addGoods(Goods goods);

    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    GoodsBo getGoodsBoById(Long id);

    /**
     * 更新商品
     * @param goods
     */
    void updateGoods(Goods goods);

    void stopGoods(Long id);

    void startGoods(Long id);

    List<GoodsBo> getGoods(String searchKey, String minDate, String maxDate);

    int getAllGoodsCount();

    GoodsDet getGoodsDetails(Long goodsId);

    Goods getGoodsById(Long id);

    PageResult<GoodsBo> getFrontGoods(int page, int size, String sort, int priceGt, int priceLte,Long cid);
}
