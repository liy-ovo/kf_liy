package com.baizhi.Controller;

import com.baizhi.entity.Address;
import com.baizhi.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService;
    @RequestMapping("/queryByUserId")
    public List<Address> queryByUserId(String id){
        return addressService.findByUserId(id);
    }
    @RequestMapping("/queryById")
    public Address queryById(String id){
        return addressService.findById(id);
    }
    @RequestMapping("/add")
    public void add(Address address){
        addressService.add(address);
    }
    @RequestMapping("/update")
    public void update(Address address){
        addressService.update(address);
    }
    @RequestMapping("/delete")
    public void delete(String id){
        addressService.delete(id);
    }
    @RequestMapping("/deleteAddresses")
    public void deleteAddresses(String[] id){
        for (String s : id) {
            addressService.delete(s);
        }
    }
}
