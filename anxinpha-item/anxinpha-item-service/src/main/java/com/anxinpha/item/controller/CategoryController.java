package com.anxinpha.item.controller;

import com.anxinpha.item.bo.CategoryBo1;
import com.anxinpha.item.pojo.Category;
import com.anxinpha.item.service.CategroySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategroySerivce categroySerivce;

    @GetMapping("list")
    public ResponseEntity<List<Category>> getCategorysById(@RequestParam(name="id",defaultValue = "0") Long parentId){

        List<Category> categories = this.categroySerivce.getCategorysById(parentId);
        if(CollectionUtils.isEmpty(categories)){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("all")
    public ResponseEntity<List<CategoryBo1>> getCategorys(){
        List<CategoryBo1> list = this.categroySerivce.getCategorys();
        if(CollectionUtils.isEmpty(list)){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("add")
    public ResponseEntity<Void> addCategory(Category category){
        this.categroySerivce.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateCategory(Category category){
        this.categroySerivce.updateCategory(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id")Long id){
        this.categroySerivce.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
