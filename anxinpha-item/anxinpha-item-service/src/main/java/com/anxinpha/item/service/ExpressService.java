package com.anxinpha.item.service;

import com.anxinpha.item.pojo.Express;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface ExpressService {
    List<Express> getExpress();


    Boolean addExpress(Express express);
}
