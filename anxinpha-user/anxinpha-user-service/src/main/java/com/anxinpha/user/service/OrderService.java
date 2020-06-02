package com.anxinpha.user.service;

import com.anxinpha.common.pojo.DataTablesResult;
import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.user.bo.OrderBo;
import com.anxinpha.user.pojo.Order;
import com.anxinpha.user.pojo.OrderInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface OrderService {

    PageResult<OrderBo> getOrders(Long userId, Integer size, Integer page);

    OrderBo getOrderDetail(String orderId);

    Boolean confirmReceipt(String orderId);

    Boolean cancelOrder(String orderId);

    Boolean delOrder(String orderId);

    Boolean payOrder(String orderId);

    Long createOrder(OrderInfo orderInfo);

    DataTablesResult getOrdersByShopId(Long shopId);

    DataTablesResult getAllOrders(Long userId);

    Integer getOrderCount();

    Boolean deliver(String orderId, String shippingName, String shippingCode, BigDecimal postFee);

    Boolean remark(Order order);
}
