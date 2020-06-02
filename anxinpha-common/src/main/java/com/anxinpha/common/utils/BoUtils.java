package com.anxinpha.common.utils;

import com.anxinpha.item.bo.GoodsBo;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.user.pojo.CartProduct;
import com.anxinpha.user.pojo.OrderItem;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author 尹硕硕
 * @description
 **/
public class BoUtils {

    public static GoodsBo Goods2GoodsBo(Goods goods){

        GoodsBo goodsBo =new GoodsBo();
//        goodsBo.setId(goods.getId());
//        goodsBo.setTitle(goods.getTitle());
//        goodsBo.setPrice(goods.getPrice().divide(BigDecimal.valueOf(100)));
//        goodsBo.setCids(goods.getCids());
//        goodsBo.setImage(goods.getImage());
//        goodsBo.setSellPoint(goods.getSellPoint());
//        goodsBo.setNum(goods.getNum());
//        goodsBo.setShopId(goods.getShopId());
//        goodsBo.setDetail(goods.getDetail());
//        if(goods.getLimitNum()==null){
//            goodsBo.setLimitNum(goods.getNum());
//        }else if(goods.getLimitNum()<0&&goods.getNum()<0) {
//            goodsBo.setLimitNum(10);
//        }else{
//            goodsBo.setLimitNum(goods.getLimitNum());
//        }
        BeanUtils.copyProperties(goods,goodsBo);
        goodsBo.setPrice(goodsBo.getPrice().divide(BigDecimal.valueOf(100)));
        return goodsBo;
    }

    public static CartProduct OrderItem2CartProduct(OrderItem orderItem){

        CartProduct cartProduct=new CartProduct();
        cartProduct.setProductId(Long.valueOf(orderItem.getItemId()));
        cartProduct.setProductName(orderItem.getTitle());
        cartProduct.setSalePrice(orderItem.getPrice());
        cartProduct.setProductNum(Long.valueOf(orderItem.getNum()));
        cartProduct.setProductImg(orderItem.getPicPath());

        return cartProduct;
    }

    public static CartProduct Goods2CartProduct(Goods goods){

        CartProduct cartProduct =new CartProduct();

        cartProduct.setProductId(goods.getId());
        cartProduct.setProductName(goods.getTitle());
        cartProduct.setSalePrice(goods.getPrice());
        cartProduct.setProductImg(goods.getImages()[0]);
        if(goods.getLimitNum()==null){
            cartProduct.setLimitNum(Long.valueOf(goods.getNum()));
        }else if(goods.getLimitNum()<0&&goods.getNum()<0) {
            cartProduct.setLimitNum((long) 10);
        }else{
            cartProduct.setLimitNum(Long.valueOf(goods.getLimitNum()));
        }
        return cartProduct;
    }
}
