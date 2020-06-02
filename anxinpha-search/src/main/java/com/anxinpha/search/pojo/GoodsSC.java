package com.anxinpha.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 尹硕硕
 * @description
 **/
@Document(indexName = "goods",type = "docs",shards = 1,replicas = 0)
public class GoodsSC {
    @Id
    private Long id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    private BigDecimal price;
    @Field(type = FieldType.Keyword,index = false)
    private String sellPoint;
    private String imageBig;
    private Date created;
    private String shopName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
