package com.anxinpha.item.bo;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public class CategoryBo1 {

    private Long id;

    private String name;

    private List<CategoryBo2> childs;

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

    public List<CategoryBo2> getChilds() {
        return childs;
    }

    public void setChilds(List<CategoryBo2> childs) {
        this.childs = childs;
    }
}
