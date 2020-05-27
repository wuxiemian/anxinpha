package com.anxinpha.user.controller;

import com.anxinpha.user.pojo.Cart;
import com.anxinpha.user.pojo.CartProduct;
import com.anxinpha.user.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("addCart")
    public ResponseEntity<Integer> addCart(Cart cart){
        Integer result = this.cartService.addCart(cart.getUserId(),cart.getProductId(),cart.getProductNum());
        if (result == null){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("cartList")
    public ResponseEntity<List<CartProduct>> getCartList(Cart cart){
        List<CartProduct> list = this.cartService.getCartList(cart.getUserId());
        if (CollectionUtils.isEmpty(list)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("cartEdit")
    public ResponseEntity<Integer> cartEdit(Cart cart){
        Integer result = this.cartService.cartEdit(cart.getUserId(),cart.getProductId(),cart.getProductNum(),cart.getChecked());
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 删除全部商品
     * @param cart
     * @return
     */
    @PutMapping("cartDel")
    public ResponseEntity<Integer> delCart(Cart cart){
        Integer result = this.cartService.delCart(cart.getUserId(),cart.getProductId(),cart.getProductNum(),cart.getChecked());
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("delCartChecked")
    public ResponseEntity<Integer> delCartChecked(Cart cart){
        Integer result = this.cartService.delCartChecked(cart.getUserId());
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("editCheckAll")
    public ResponseEntity<Integer> editCheckAll(Cart cart){
        Integer result = this.cartService.editCheckAll(cart.getUserId(),cart.getChecked());
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

}
