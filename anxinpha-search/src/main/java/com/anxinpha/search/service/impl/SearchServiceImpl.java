package com.anxinpha.search.service.impl;

import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.search.client.ShopClient;
import com.anxinpha.search.pojo.GoodsSC;
import com.anxinpha.search.repository.GoodsRepository;
import com.anxinpha.search.service.SearchService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ShopClient shopClient;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public GoodsSC buildGoods(Goods goods) {
        GoodsSC goodsSC = new GoodsSC();
        goodsSC.setId(goods.getId());
        goodsSC.setTitle(goods.getTitle());
        goodsSC.setImageBig(goods.getImage());
        goodsSC.setSellPoint(goods.getSellPoint());
        goodsSC.setPrice(goods.getPrice());
        goodsSC.setCreated(goods.getCreated());
        String shopName = shopClient.getShopName(goods.getShopId());
        goodsSC.setShopName(shopName);
        return goodsSC;
    }

    @Override
    public PageResult<GoodsSC> getGoods(String key, Integer size, Integer page, String sort, String priceGt, String priceLte) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title",key));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page-1,size));
        Page<GoodsSC> search = this.goodsRepository.search(nativeSearchQueryBuilder.build());
        return new PageResult<>(search.getTotalElements(),search.getContent());
    }

}
