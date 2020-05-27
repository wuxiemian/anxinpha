package com.anxinpha.user.service;

import com.anxinpha.user.pojo.Address;

import java.util.List;

/**
 * @author 尹硕硕
 * @description
 **/
public interface AddressService {
    List<Address> getAddresss(Long userId);

    Boolean addAddress(Address address);

    Boolean delAddress(Long id);

    void updateAddress(Address address);
}
