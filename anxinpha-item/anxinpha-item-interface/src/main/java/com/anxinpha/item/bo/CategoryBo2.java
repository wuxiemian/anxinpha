package com.anxinpha.item.bo;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public class CategoryBo2 {
    private Long id;

    private String name;

    private List<CategoryBo3> childs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryBo3> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryBo3> childs) {
        this.childs = childs;
    }
}
