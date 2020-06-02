package com.anxinpha.item.controller;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.item.pojo.Express;
import com.anxinpha.item.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
@RequestMapping("express")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @GetMapping("list")
    public ResponseEntity<List<Express>> getExpress(){
        List<Express> list = this.expressService.getExpress();
        if (CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("tablelist")
    public ResponseEntity<DataTablesResult> getTableExpress(){
        DataTablesResult dataTablesResult = new DataTablesResult();
        List<Express> list = this.expressService.getExpress();
        if (CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        }
        dataTablesResult.setData(list);
        return ResponseEntity.ok(dataTablesResult);
    }

    @PostMapping("add")
    public ResponseEntity<Boolean> addExpress(Express express){
        Boolean result = this.expressService.addExpress(express);
        return ResponseEntity.ok(result);
    }
}
