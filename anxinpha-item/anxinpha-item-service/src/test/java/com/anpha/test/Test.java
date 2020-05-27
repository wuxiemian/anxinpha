package com.anpha.test;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public class Test {
    @org.junit.Test
    public void test1(){
        BigDecimal bigDecimal = BigDecimal.valueOf(100);
        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(100)));
    }
    @org.junit.Test
    public void test2(){
        List<String> list = new ArrayList<>();
        list.add("asd");
        list.add("222");
        System.out.println(JSONObject.toJSONString(list));
    }
    @org.junit.Test
    public void test3(){
        StringBuilder aa = new StringBuilder();
        if (aa.toString().equals("")){
            aa.append("123");
        }else {
            aa.append("234");
        }

        System.out.println(aa.toString());
    }
}
