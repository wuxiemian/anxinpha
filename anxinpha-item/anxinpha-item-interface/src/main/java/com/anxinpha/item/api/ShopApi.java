package com.anxinpha.item.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 尹硕硕
 * @description
 **/
public interface ShopApi {

    @GetMapping("shop/getShopName")
    String getShopName(@RequestParam("shopId")Long shopId);

}
