package com.anxinpha.user.service.impl;

import com.anxinpha.common.utils.BoUtils;
import com.anxinpha.item.pojo.Goods;
import com.anxinpha.user.client.GoodsClient;
import com.anxinpha.user.pojo.Cart;
import com.anxinpha.user.pojo.CartProduct;
import com.anxinpha.user.service.CartService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private GoodsClient goodsClient;
    private static final String CART_PRE = "CART";

    @Override
    public Integer addCart(Long userId, Long itemId, int num) {
        //hash: "key:用户id" field："商品id" value："商品信息"
        Boolean hexists = redisTemplate.opsForHash().hasKey(CART_PRE + ":" + userId, itemId + "");
        //如果存在数量相加
        if (hexists) {
            Object object = redisTemplate.opsForHash().get(CART_PRE + ":" + userId, itemId + "");
            if (object == null){
                return 0;
            }
            String json = object.toString();
            CartProduct cartProduct = new Gson().fromJson(json,CartProduct.class);
            cartProduct.setProductNum(cartProduct.getProductNum() + num);
            redisTemplate.opsForHash().put(CART_PRE + ":" + userId, itemId + "", new Gson().toJson(cartProduct));
            return 1;
        }
        //如果不存在，根据商品id取商品信息
        Goods goods = goodsClient.getGoodsById(itemId);
        if(goods==null){
            return 0;
        }
        CartProduct cartProduct= BoUtils.Goods2CartProduct(goods);
        cartProduct.setProductNum((long) num);
        cartProduct.setChecked("1");
        redisTemplate.opsForHash().put(CART_PRE  + ":" + userId, itemId + "", new Gson().toJson(cartProduct));
        return 1;
    }

    @Override
    public List<CartProduct> getCartList(Long userId) {
        List<Object> jsonList = redisTemplate.opsForHash().values(CART_PRE + ":" + userId);
        List<CartProduct> list = new ArrayList<>();
        for (Object json : jsonList) {
            CartProduct cartProduct = new Gson().fromJson(json.toString(),CartProduct.class);
            cartProduct.setSalePrice(cartProduct.getSalePrice().divide(BigDecimal.valueOf(100)));
            list.add(cartProduct);
        }
        return list;
    }

    @Override
    public Integer cartEdit(Long userId, Long productId, int productNum, String checked) {
        Object object = redisTemplate.opsForHash().get(CART_PRE + ":" + userId, productId + "");
        if (object == null){
            return 0;
        }
        String json = object.toString();
        CartProduct cartProduct = new Gson().fromJson(json,CartProduct.class);
        cartProduct.setProductNum((long) productNum);
        cartProduct.setChecked(checked);
        redisTemplate.opsForHash().put(CART_PRE + ":" + userId, productId + "", new Gson().toJson(cartProduct));
        return 1;
    }

    @Override
    public Integer delCart(Long userId, Long productId, int productNum, String checked) {
        this.redisTemplate.opsForHash().delete(CART_PRE + ":" + userId, productId + "");
        return 1;
    }

    @Override
    public Integer delCartChecked(Long userId) {
        List<Object> objects = redisTemplate.opsForHash().values(CART_PRE+":"+userId);
        List<String> jsonList = objects.stream().map(object-> (String)object).collect(Collectors.toList());
        for (String json : jsonList) {
            CartProduct cartProduct = new Gson().fromJson(json,CartProduct.class);
            if("1".equals(cartProduct.getChecked())) {
                redisTemplate.opsForHash().delete(CART_PRE+":"+userId, cartProduct.getProductId()+"");
            }
        }
        return 1;
    }

    @Override
    public Integer editCheckAll(Long userId, String checked) {
        List<Object> objects = redisTemplate.opsForHash().values(CART_PRE + ":" + userId);
        List<String> jsonList = objects.stream().map(object -> (String)object).collect(Collectors.toList());
        for (String json : jsonList) {
            CartProduct cartProduct = new Gson().fromJson(json,CartProduct.class);
            if("true".equals(checked)) {
                cartProduct.setChecked("1");
            }else if("false".equals(checked)) {
                cartProduct.setChecked("0");
            }else {
                return 0;
            }
            redisTemplate.opsForHash().put(CART_PRE + ":" + userId, cartProduct.getProductId() + "", new Gson().toJson(cartProduct));
        }
        return 1;
    }
}
