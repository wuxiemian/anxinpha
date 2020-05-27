package com.anxinpha.item.service.impl;

import com.anxinpha.item.bo.CategoryBo1;
import com.anxinpha.item.bo.CategoryBo2;
import com.anxinpha.item.bo.CategoryBo3;
import com.anxinpha.item.mapper.CategoryMapper;
import com.anxinpha.item.pojo.Category;
import com.anxinpha.item.service.CategroySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class CategoryServiceImpl implements CategroySerivce {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategorysById(Long parentId) {
        Category category = new Category();
        category.setParentId(parentId);
        return this.categoryMapper.select(category);
    }

    @Override
    public void addCategory(Category category) {
        category.setCreated(new Date());
        category.setUpdated(new Date());
        this.categoryMapper.insertSelective(category);
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdated(new Date());
        this.categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryMapper.selectByPrimaryKey(id);
        //判断是否是父节点，是则删除其下所有节点
        if(category.getIsParent()){
            Category record = new Category();
            record.setParentId(id);
            this.categoryMapper.delete(record);
        }
        this.categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CategoryBo1> getCategorys() {
        Category record = new Category();
        record.setParentId(0L);
        List<Category> categories = this.categoryMapper.select(record);
        List<CategoryBo1> list = categories.stream().map(category -> {
            CategoryBo1 categoryBo1 = new CategoryBo1();
            categoryBo1.setId(category.getId());
            categoryBo1.setName(category.getName());
            Category record1 = new Category();
            record1.setParentId(category.getId());
            List<Category> categories1 = this.categoryMapper.select(record1);
            List<CategoryBo2> list1 = categories1.stream().map(category1 -> {
                CategoryBo2 categoryBo2 = new CategoryBo2();
                categoryBo2.setId(category1.getId());
                categoryBo2.setName(category1.getName());
                Category record2 = new Category();
                record2.setParentId(category1.getId());
                List<Category> categories2 = this.categoryMapper.select(record2);
                List<CategoryBo3> list2 = categories2.stream().map(category2 -> {
                    CategoryBo3 categoryBo3 = new CategoryBo3();
                    categoryBo3.setId(category2.getId());
                    categoryBo3.setName(category2.getName());
                    return categoryBo3;
                }).collect(Collectors.toList());
                categoryBo2.setChilds(list2);
                return categoryBo2;
            }).collect(Collectors.toList());
            categoryBo1.setChilds(list1);
            return categoryBo1;
        }).collect(Collectors.toList());
        return list;
    }
}
