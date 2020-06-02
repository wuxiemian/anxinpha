package com.anxinpha.search.contorller;

import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.search.pojo.GoodsSC;
import com.anxinpha.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
public class SearchContorller {

    @Autowired
    private SearchService searchService;
    @GetMapping("goods")
    public ResponseEntity<PageResult<GoodsSC>> getGoods(
            @RequestParam(value = "key",defaultValue = "")String key,
            @RequestParam(value = "size",defaultValue = "8")Integer size,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "sort",defaultValue = "")String sort,
            @RequestParam(value = "priceGt",defaultValue = "")String priceGt,
            @RequestParam(value = "priceLte",defaultValue = "")String priceLte

    ){
        PageResult<GoodsSC> pageResult = this.searchService.getGoods(key,size,page,sort,priceGt,priceLte);
        if (CollectionUtils.isEmpty(pageResult.getData())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }
}
