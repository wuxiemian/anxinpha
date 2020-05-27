package com.anxinpha.item.controller;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.item.pojo.Shop;
import com.anxinpha.item.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("list")
    public ResponseEntity<DataTablesResult> getAllShops(
            @RequestParam(value = "searchKey",required = false) String searchKey,
            @RequestParam(value = "minDate",required = false) String minDate,
            @RequestParam(value = "maxDate",required = false) String maxDate){
        DataTablesResult dataTablesResult = new DataTablesResult();
        List<Shop> shops = this.shopService.getAllShops(searchKey,minDate,maxDate);
        if (CollectionUtils.isEmpty(shops)){
            return ResponseEntity.notFound().build();
        }
        dataTablesResult.setData(shops);
        return ResponseEntity.ok(dataTablesResult);
    }

    @GetMapping("count")
    public  ResponseEntity<DataTablesResult> getShopCount(){
        DataTablesResult dataTablesResult = new DataTablesResult();
        int count = this.shopService.getShopCount();
        dataTablesResult.setRecordsTotal(count);
        return ResponseEntity.ok(dataTablesResult);
    }

    @PostMapping("add")
    public ResponseEntity<Void> addShop(Shop shop){
        this.shopService.addShop(shop);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateShop(Shop shop){
        this.shopService.updateShop(shop);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable("id") Long id){
        this.shopService.deleteShop(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("shopName")
    public ResponseEntity<Boolean> verifyShopName(@RequestParam("shopName")String shopName){
        ;
        if(this.shopService.getShopByShopName(shopName)!=null){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("remove/{ids}")
    public ResponseEntity<Void> deleteShops(@PathVariable("ids")String ids){
        String[] shopIds = ids.split(",");
        for (String shopId : shopIds) {
            this.shopService.deleteShop(Long.parseLong(shopId));
        }
        return ResponseEntity.ok().build();
    }

}
