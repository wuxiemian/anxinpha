package com.anxinpha.search.test;

import com.anxinpha.AnxinPhaSearchApplication;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.search.client.GoodsClient;
import com.anxinpha.search.pojo.GoodsSC;
import com.anxinpha.search.repository.GoodsRepository;
import com.anxinpha.search.service.SearchService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 尹硕硕
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private SearchService searchService;
    @Autowired
    private GoodsClient goodsClient;

    @org.junit.Test
    public void test(){

        this.template.createIndex(GoodsSC.class);
        this.template.putMapping(GoodsSC.class);

        List<Goods> goods = goodsClient.getGoods();
        List<GoodsSC> goodsSCS = goods.stream().map(good -> searchService.buildGoods(good)).collect(Collectors.toList());

        this.goodsRepository.saveAll(goodsSCS);

    }
}
