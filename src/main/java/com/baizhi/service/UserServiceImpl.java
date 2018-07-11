package com.baizhi.service;

import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.util.MD5Utils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User login(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()==0){
            return null;
        }
        String md5Code = MD5Utils.getMd5Code(password + users.get(0).getSalt());
        if(!users.get(0).getPassword().equals(md5Code)){
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public List<User> findByUser(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public User findById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(User user) {
        user.setId(UUID.randomUUID().toString());
        String salt = MD5Utils.getSalt(6);
        user.setSalt(salt);
        String md5Code = MD5Utils.getMd5Code(user.getPassword() + salt);
        user.setPassword(md5Code);
        user.setHeadImg("/back/user/img/defaultImage.jpg");
        user.setCreateTime(new Date());
        user.setStatus(1);
        userMapper.insertSelective(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
