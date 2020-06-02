package com.anxinpha.search.service;

import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.search.pojo.GoodsSC;

/**
 * @author 尹硕硕
 * @description
 **/
public interface SearchService {

    GoodsSC buildGoods(Goods goods);

    PageResult<GoodsSC> getGoods(String key, Integer size, Integer page, String sort, String priceGt, String priceLte);
}
