package com.anxinpha.item.api;

import com.anxinpha.item.pojo.Goods;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 尹硕硕
 * @description
 **/
public interface GoodsApi {
    @GetMapping("goods/one")
    public Goods getGoodsById(@RequestParam("id")Long id);
}
