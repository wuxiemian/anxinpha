package com.anxinpha.item.controller;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.item.bo.GoodsBo;
import com.anxinpha.item.bo.GoodsDet;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.item.pojo.Panel;
import com.anxinpha.item.service.ContentService;
import com.anxinpha.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ContentService contentService;

    @GetMapping("list")
    public ResponseEntity<DataTablesResult> getShopGoods(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "searchKey",required = false) String searchKey,
            @RequestParam(value = "minDate",required = false) String minDate,
            @RequestParam(value = "maxDate",required = false) String maxDate){
        DataTablesResult dataTablesResult = new DataTablesResult();
        List<Goods> goods = this.goodsService.getShopGoods(id,searchKey,minDate,maxDate);
        if (CollectionUtils.isEmpty(goods)){
            return ResponseEntity.notFound().build();
        }
        dataTablesResult.setData(goods);
        return ResponseEntity.ok(dataTablesResult);
    }

    @GetMapping("all")
    public ResponseEntity<DataTablesResult> getGoods(
            @RequestParam(value = "userId",required = false)Long userId,
            @RequestParam(value = "searchKey",required = false) String searchKey,
            @RequestParam(value = "minDate",required = false) String minDate,
            @RequestParam(value = "maxDate",required = false) String maxDate){
        DataTablesResult dataTablesResult = new DataTablesResult();
        List<GoodsBo> goods = this.goodsService.getGoods(userId,searchKey,minDate,maxDate);
        if (CollectionUtils.isEmpty(goods)){
            return ResponseEntity.notFound().build();
        }
        dataTablesResult.setData(goods);
        return ResponseEntity.ok(dataTablesResult);
    }

    @GetMapping("allGoods")
    public ResponseEntity<PageResult<GoodsBo>> getFrontGoods(
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam(value = "size",defaultValue = "20") int size,
            @RequestParam(value = "sort",defaultValue = "") String sort,
            @RequestParam(value = "priceGt",defaultValue = "-1") int priceGt,
            @RequestParam(value = "PriceLte",defaultValue = "-1") int priceLte,
            @RequestParam(value = "cid",defaultValue = "-1")Long cid){
        PageResult<GoodsBo> pageResult = this.goodsService.getFrontGoods(page,size,sort,priceGt,priceLte,cid);
        if (CollectionUtils.isEmpty(pageResult.getData())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodsBo> getGoodsBoById(@PathVariable("id")Long id){
        GoodsBo goodsBo = this.goodsService.getGoodsBoById(id);
        if(goodsBo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(goodsBo);
    }

    @GetMapping("one")
    public ResponseEntity<Goods> getGoodsById(@RequestParam("id")Long id){
        Goods goods = this.goodsService.getGoodsById(id);
        return ResponseEntity.ok(goods);
    }

    @GetMapping("count/{id}")
    public  ResponseEntity<DataTablesResult> getGoodsCount(@PathVariable("id")Long id){
        DataTablesResult dataTablesResult = new DataTablesResult();
        int count = this.goodsService.getGoodsCount(id);
        dataTablesResult.setRecordsTotal(count);
        return ResponseEntity.ok(dataTablesResult);
    }
    @GetMapping("allcount")
    public  ResponseEntity<DataTablesResult> getAllGoodsCount(){
        DataTablesResult dataTablesResult = new DataTablesResult();
        int count = this.goodsService.getAllGoodsCount();
        dataTablesResult.setRecordsTotal(count);
        return ResponseEntity.ok(dataTablesResult);
    }

    @PostMapping("add")
    public ResponseEntity<Void> addGoods(Goods goods){
        this.goodsService.addGoods(goods);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateGoods(Goods goods){
        this.goodsService.updateGoods(goods);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<Void> deleteGoods(@PathVariable("id") String id){
        this.goodsService.deleteGoods(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("listSearch")
    public ResponseEntity<DataTablesResult> getGoodsBySearch(){
        DataTablesResult dataTablesResult = new DataTablesResult();
        return ResponseEntity.ok(dataTablesResult);

    }

    @PutMapping("stop/{id}")
    public ResponseEntity<Void> stopGoods(@PathVariable("id") Long id){
        this.goodsService.stopGoods(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("start/{id}")
    public ResponseEntity<Void> startGoods(@PathVariable("id") Long id){
        this.goodsService.startGoods(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("home")
    public ResponseEntity<List<Panel>> getHome(){
        List<Panel> panelList = this.contentService.getHome();
        if (CollectionUtils.isEmpty(panelList)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(panelList);
    }

    @GetMapping("goodsDetails")
    public ResponseEntity<GoodsDet> getGoodsDetails(@RequestParam("goodsId")Long goodsId){
        GoodsDet goodsDet = this.goodsService.getGoodsDetails(goodsId);
        if (goodsDet == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(goodsDet);
    }
    @GetMapping("allfein")
    public ResponseEntity<List<Goods>> getGoods(){
        List<Goods> goods = this.goodsService.getGoods1();
        return ResponseEntity.ok(goods);
    }
}
