package com.anxinpha.item.service.impl;

import com.anxinpha.item.mapper.ExpressMapper;
import com.anxinpha.item.pojo.Express;
import com.anxinpha.item.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public List<Express> getExpress() {
        return this.expressMapper.selectAll();
    }

    @Override
    public Boolean addExpress(Express express) {
        express.setCreated(new Date());
        express.setUpdated(new Date());
        this.expressMapper.insertSelective(express);
        return true;
    }


}
