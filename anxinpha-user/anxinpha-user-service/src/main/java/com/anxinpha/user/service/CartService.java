package com.anxinpha.user.service;

import com.anxinpha.user.pojo.Cart;
import com.anxinpha.user.pojo.CartProduct;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface CartService {

    Integer addCart(Long userId, Long productId, int productNum);

    List<CartProduct> getCartList(Long userId);

    Integer cartEdit(Long userId, Long productId, int productNum, String checked);

    Integer delCart(Long userId, Long productId, int productNum, String checked);

    Integer delCartChecked(Long userId);

    Integer editCheckAll(Long userId, String checked);
}
