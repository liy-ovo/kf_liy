package com.baizhi.service;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminExample;
import com.baizhi.util.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if(admins.size()==0){
            return null;
        }
        String md5Code = MD5Utils.getMd5Code(password + admins.get(0).getSalt());
        if(!admins.get(0).getPassword().equals(md5Code)){
            return null;
        }
        return admins.get(0);
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public List<Admin> findByAdmin(AdminExample example) {
        return adminMapper.selectByExample(example);
    }

    @Override
    public Admin findById(String id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        String salt = MD5Utils.getSalt(6);
        admin.setSalt(salt);
        String md5Code = MD5Utils.getMd5Code(admin.getPassword() + salt);
        admin.setPassword(md5Code);
        adminMapper.insertSelective(admin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void delete(String id) {
        adminMapper.deleteByPrimaryKey(id);
    }
}
