package com.baizhi.service;

import com.baizhi.dao.AddressMapper;
import com.baizhi.entity.Address;
import com.baizhi.entity.AddressExample;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService{
    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<Address> findByUserId(String id) {
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(id);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Override
    public Address findById(String id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Address address) {
        address.setId(UUID.randomUUID().toString());
        address.setCreateTime(new Date());
        addressMapper.insertSelective(address);
    }

    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(String id) {
        addressMapper.deleteByPrimaryKey(id);
    }
}
