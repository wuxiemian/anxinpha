package com.anxinpha.item.service;

import com.anxinpha.item.bo.CategoryBo1;
import com.anxinpha.item.pojo.Category;

import java.util.List;

/**
 * @author 尹硕硕
 * @description 分类service
 **/
public interface CategroySerivce {
    /**
     * 通过父节点id查询分类
     * @param parentId
     * @return
     */
    List<Category> getCategorysById(Long parentId);

    /**
     * 添加分类节点
     * @param category
     */
    void addCategory(Category category);

    /**
     * 修改分类节点
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除分类节点
     * @param id
     */
    void deleteCategory(Long id);

    List<CategoryBo1> getCategorys();
}
