package com.anxinpha.user.service.impl;

import com.anxinpha.user.mapper.AddressMapper;
import com.anxinpha.user.mapper.UserMapper;
import com.anxinpha.user.pojo.Address;
import com.anxinpha.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Address> getAddresss(Long userId) {
        Address address = new Address();
        address.setUserId(userId);
        List<Address> list = this.addressMapper.select(address);
        return list;
    }

    @Override
    public Boolean addAddress(Address address) {
        if (address.getIsDefault()){
            Address record = new Address();
            record.setIsDefault(true);
            Address record1 = this.addressMapper.selectOne(record);
            record1.setIsDefault(false);
            this.addressMapper.updateByPrimaryKeySelective(record1);
        }
        this.addressMapper.insertSelective(address);
        return true;
    }

    @Override
    public Boolean delAddress(Long id) {
        this.addressMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public void updateAddress(Address address) {
        if (address.getIsDefault()){
            Address record = new Address();
            record.setIsDefault(true);
            Address record1 = this.addressMapper.selectOne(record);
            record1.setIsDefault(false);
            this.addressMapper.updateByPrimaryKeySelective(record1);
        }
        this.addressMapper.updateByPrimaryKeySelective(address);
    }
}
