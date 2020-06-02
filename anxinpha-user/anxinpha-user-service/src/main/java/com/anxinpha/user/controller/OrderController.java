package com.anxinpha.user.controller;

import com.anxinpha.common.pojo.DataTablesResult;
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

import java.math.BigDecimal;
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

    @GetMapping("odlist")
    public ResponseEntity<DataTablesResult> getOrdersByShopId(@RequestParam("shopId")Long shopId){
        DataTablesResult dataTablesResult = this.orderService.getOrdersByShopId(shopId);
        if (CollectionUtils.isEmpty(dataTablesResult.getData())){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dataTablesResult);
    }

    @GetMapping("allorder")
    public ResponseEntity<DataTablesResult> getAllOrders(@RequestParam(value = "userId",required = false)Long userId){
        DataTablesResult dataTablesResult = this.orderService.getAllOrders(userId);
        if (CollectionUtils.isEmpty(dataTablesResult.getData())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dataTablesResult);
    }

    @GetMapping("orderCount")
    public ResponseEntity<Integer> getOrderCount(){
        Integer result = this.orderService.getOrderCount();
        if (result==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
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

    @PostMapping("deliver")
    public ResponseEntity<Boolean> deliver(
            @RequestParam("orderId") String orderId,
            @RequestParam("shippingName") String shippingName,
            @RequestParam("shippingCode") String shippingCode,
            @RequestParam("postFee") BigDecimal postFee){

        Boolean result = this.orderService.deliver(orderId, shippingName, shippingCode, postFee);
        return ResponseEntity.ok(result);
    }
    @PostMapping("orderRemark")
    public ResponseEntity<Boolean> remark(Order order){
        Boolean result = this.orderService.remark(order);
        if (result==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
