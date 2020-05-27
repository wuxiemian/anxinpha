package com.anxinpha.user.controller;

import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.user.bo.OrderBo;
import com.anxinpha.user.pojo.Order;
import com.anxinpha.user.pojo.OrderInfo;
import com.anxinpha.user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("orderList")
    public ResponseEntity<PageResult<OrderBo>> getOrders(@RequestParam("userId")Long userId, @RequestParam("size")Integer size, @RequestParam("page")Integer page){
        PageResult<OrderBo> pageResult = this.orderService.getOrders(userId,size,page);
        if (CollectionUtils.isEmpty(pageResult.getData())){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("orderDetail")
    public ResponseEntity<OrderBo> getOrderDetail(@RequestParam("orderId")String orderId){
        OrderBo orderBo = this.orderService.getOrderDetail(orderId);
        if (orderBo == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderBo);
    }

    @PutMapping("confirmReceipt")
    public ResponseEntity<Boolean> confirmReceipt(@RequestParam("orderId")String orderId){
        Boolean result = this.orderService.confirmReceipt(orderId);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("cancelOrder")
    public ResponseEntity<Boolean> cancelOrder(@RequestParam("orderId")String orderId){
        Boolean result = this.orderService.cancelOrder(orderId);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("delOrder/{orderId}")
    public ResponseEntity<Boolean> delOrder(@PathVariable("orderId")String orderId){
        Boolean result = this.orderService.delOrder(orderId);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("payOrder")
    public ResponseEntity<Boolean> payOrder(@RequestParam("orderId")String orderId){
        Boolean result = this.orderService.payOrder(orderId);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("addOrder")
    public ResponseEntity<Long> createOrder(@RequestBody OrderInfo orderInfo){
        Long orderId = this.orderService.createOrder(orderInfo);
        if (orderId == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderId);
    }
}
