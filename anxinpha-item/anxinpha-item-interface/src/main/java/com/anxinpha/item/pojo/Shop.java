package com.anxinpha.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 尹硕硕
 * @description
 **/
@Table(name = "tb_shop")
public class Shop {
    @Id
    private Long id;
    private String shopName;
    private String shopIcon;
    private String shopDesc;
    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }
}
