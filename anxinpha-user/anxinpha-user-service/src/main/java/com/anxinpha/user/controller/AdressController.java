package com.anxinpha.user.controller;

import com.anxinpha.user.pojo.Address;
import com.anxinpha.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class AdressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("addressList")
    public ResponseEntity<List<Address>> getAddresss(@RequestParam("userId")Long userId){
        List<Address> list = this.addressService.getAddresss(userId);
        if (CollectionUtils.isEmpty(list)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(list);
    }
    @PostMapping("addAddress")
    public ResponseEntity<Boolean> addAddress(Address address){
        Boolean result = this.addressService.addAddress(address);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("delAddress/{id}")
    public ResponseEntity<Boolean> delAddress(@PathVariable("id")Long id){
        Boolean result = this.addressService.delAddress(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("updateAddress")
    public ResponseEntity<Void> updateAddress(Address address){
        this.addressService.updateAddress(address);
        return ResponseEntity.ok().build();
    }
}
