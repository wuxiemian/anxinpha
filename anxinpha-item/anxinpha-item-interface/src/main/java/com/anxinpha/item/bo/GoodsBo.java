package com.anxinpha.item.bo;

import com.anxinpha.item.pojo.Goods;

/**
 * @author 尹硕硕
 * @description
 **/
public class GoodsBo extends Goods {
    private String cname;
    private String shopname;
    private String imageBig;

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
