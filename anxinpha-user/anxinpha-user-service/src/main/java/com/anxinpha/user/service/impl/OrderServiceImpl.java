package com.anxinpha.user.service.impl;

import com.anxinpha.auth.pojo.UserInfo;
import com.anxinpha.common.pojo.PageResult;
import com.anxinpha.common.utils.BoUtils;
import com.anxinpha.user.bo.OrderBo;
import com.anxinpha.user.mapper.AddressMapper;
import com.anxinpha.user.mapper.OrderItemMapper;
import com.anxinpha.user.mapper.OrderMapper;
import com.anxinpha.user.mapper.UserMapper;
import com.anxinpha.user.pojo.*;
import com.anxinpha.user.service.OrderService;
import com.anxinpha.user.utils.OrderIdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;
    private static final String CART_PRE="CART";
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageResult<OrderBo> getOrders(Long userId, Integer size, Integer page) {
        Order order = new Order();
        order.setUserId(userId);
        PageHelper.startPage(page,size);
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        example.setOrderByClause("create_time DESC");
        List<Order> list = this.orderMapper.selectByExample(example);
        List<OrderBo> orderBos = list.stream().map(tbOrder ->{
            OrderBo orderBo=new OrderBo();
            //orderId
            orderBo.setOrderId(Long.valueOf(tbOrder.getOrderId()));
            //orderStatus
            orderBo.setOrderStatus(String.valueOf(tbOrder.getStatus()));
            //createDate
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = formatter.format(tbOrder.getCreateTime());
            orderBo.setCreateDate(date);
            //address
            Address address = this.addressMapper.selectByPrimaryKey(tbOrder.getAddressId());

            orderBo.setAddressInfo(address);
            //orderTotal
            if(tbOrder.getPayment()==null){
                orderBo.setOrderTotal(new BigDecimal(0));
            }else{
                orderBo.setOrderTotal(tbOrder.getPayment());
            }
            //goodsList
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(tbOrder.getOrderId());
            List<OrderItem> orderItems = this.orderItemMapper.select(orderItem);
            List<CartProduct> listProduct=new ArrayList<>();
            for(OrderItem tbOrderItem:orderItems){

                CartProduct cartProduct= BoUtils.OrderItem2CartProduct(tbOrderItem);

                listProduct.add(cartProduct);
            }
            orderBo.setGoodsList(listProduct);
            return orderBo;
        }).collect(Collectors.toList());
        PageInfo<OrderBo> pageInfo = new PageInfo<>(orderBos);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public OrderBo getOrderDetail(String orderId) {
        Order order = this.orderMapper.selectByPrimaryKey(orderId);
        if (order == null){
            return null;
        }
        OrderBo orderBo = new OrderBo();
        //orderId
        orderBo.setOrderId(Long.valueOf(order.getOrderId()));
        //orderStatus
        orderBo.setOrderStatus(String.valueOf(order.getStatus()));
        //createDate
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createDate = formatter.format(order.getCreateTime());
        orderBo.setCreateDate(createDate);
        //payDate
        if(order.getPaymentTime()!=null){
            String payDate = formatter.format(order.getPaymentTime());
            orderBo.setPayDate(payDate);
        }
        //closeDate
        if(order.getCloseTime()!=null){
            String closeDate = formatter.format(order.getCloseTime());
            orderBo.setCloseDate(closeDate);
        }
        //finishDate
        if(order.getEndTime()!=null&&order.getStatus()==4){
            String finishDate = formatter.format(order.getEndTime());
            orderBo.setFinishDate(finishDate);
        }
        Address address = this.addressMapper.selectByPrimaryKey(order.getAddressId());

        orderBo.setAddressInfo(address);
        //orderTotal
        if(order.getPayment()==null){
            orderBo.setOrderTotal(new BigDecimal(0));
        }else{
            orderBo.setOrderTotal(order.getPayment());
        }
        //goodsList
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        List<OrderItem> orderItems = this.orderItemMapper.select(orderItem);
        List<CartProduct> listProduct=new ArrayList<>();
        for(OrderItem tbOrderItem:orderItems){

            CartProduct cartProduct= BoUtils.OrderItem2CartProduct(tbOrderItem);

            listProduct.add(cartProduct);
        }
        orderBo.setGoodsList(listProduct);
        return orderBo;
    }

    @Override
    public Boolean confirmReceipt(String orderId) {
        Order order = this.orderMapper.selectByPrimaryKey(orderId);
        if (order == null){
            return null;
        }
        order.setStatus(4);
        order.setEndTime(new Date());
        order.setUpdateTime(new Date());
        this.orderMapper.updateByPrimaryKeySelective(order);
        return true;
    }

    @Override
    public Boolean cancelOrder(String orderId) {
        Order order = this.orderMapper.selectByPrimaryKey(orderId);
        if (order == null){
            return null;
        }
        order.setStatus(5);
        order.setCloseTime(new Date());
        order.setUpdateTime(new Date());
        this.orderMapper.updateByPrimaryKeySelective(order);
        return true;
    }

    @Override
    public Boolean delOrder(String orderId) {
        this.orderMapper.deleteByPrimaryKey(orderId);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        this.orderItemMapper.delete(orderItem);
        return true;
    }

    @Override
    public Boolean payOrder(String orderId) {
        Order order = this.orderMapper.selectByPrimaryKey(orderId);
        if (order == null){
            return null;
        }
        order.setStatus(1);
        order.setPaymentTime(new Date());
        order.setUpdateTime(new Date());
        this.orderMapper.updateByPrimaryKeySelective(order);
        return true;
    }

    @Override
    @Transactional
    public Long createOrder(OrderInfo orderInfo) {
        User user=this.userMapper.selectByPrimaryKey(Long.valueOf(orderInfo.getUserId()));
        if(user==null){
            return null;
        }

        Order order=new Order();
        //生成订单ID
        Long orderId = OrderIdUtils.getRandomId();
        order.setOrderId(String.valueOf(orderId));
        order.setUserId(Long.valueOf(orderInfo.getUserId()));
        order.setBuyerMessage(orderInfo.getBuyerMessage());
        order.setPaymentType(orderInfo.getPaymentType());
        order.setBuyerNick(user.getUsername());
        order.setAddressId(orderInfo.getAddressId());
        order.setPayment(orderInfo.getOrderTotal());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        //0、未付款，1、已付款，2、未发货，3、已发货，4、交易成功，5、交易关闭，6、交易失败
        order.setStatus(0);
        if (orderInfo.getPaymentType()==2){
            order.setStatus(2);
        }

        this.orderMapper.insertSelective(order);

        List<CartProduct> list=orderInfo.getGoodsList();
        for(CartProduct cartProduct:list){
            OrderItem orderItem=new OrderItem();
            //生成订单商品ID;
            orderItem.setId(String.valueOf(OrderIdUtils.getRandomId()));
            orderItem.setItemId(String.valueOf(cartProduct.getProductId()));
            orderItem.setOrderId(String.valueOf(orderId));
            orderItem.setNum(Math.toIntExact(cartProduct.getProductNum()));
            orderItem.setPrice(cartProduct.getSalePrice());
            orderItem.setTitle(cartProduct.getProductName());
            orderItem.setPicPath(cartProduct.getProductImg());
            orderItem.setTotalFee(cartProduct.getSalePrice().multiply(BigDecimal.valueOf(cartProduct.getProductNum())));

            this.orderItemMapper.insertSelective(orderItem);

            //删除购物车中含该订单的商品
            try{
                List<Object> objects = redisTemplate.opsForHash().values(CART_PRE + ":" + orderInfo.getUserId());
                List<String> jsonList = objects.stream().map(object->(String)object).collect(Collectors.toList());
                for (String json : jsonList) {
                    CartProduct cart = new Gson().fromJson(json,CartProduct.class);
                    if(cart.getProductId().equals(cartProduct.getProductId())){
                        this.redisTemplate.opsForHash().delete(CART_PRE + ":" + orderInfo.getUserId(),cart.getProductId()+"");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return orderId;
    }
}
