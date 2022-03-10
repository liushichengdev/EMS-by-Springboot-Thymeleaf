package com.springboot.ems.service.impl;

import com.springboot.ems.entity.User;
import com.springboot.ems.mapper.UserMapper;
import com.springboot.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

@Service
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    public UserServiceImpl(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }


    @Override
    public void register(User user) {
        //1. 根据用户名查询数据库是否存在用户
        User userDB=userMapper.findByUserName(user.getUsername());
        //int userNum=userMapper.countByUserName(user.getUsername());
        //2. 判断用户是否存在
        if(!ObjectUtils.isEmpty(userDB)) throw new RuntimeException("当前用户名已经被注册");
        //if(userNum!=0) throw new RuntimeException("当前用户已经被注册");
        // 3.注册用户 & 密码加密
        String newPassword= DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(newPassword);
        userMapper.save(user);
    }

    @Override
    public User login(String username, String password) {
        //1. 根据用户名查询用户
        User user = userMapper.findByUserName(username);
        if(ObjectUtils.isEmpty(user)) throw new RuntimeException("用户名不正确！");
        //2. 比较密码
        String md5Password=DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if(!user.getPassword().equals(md5Password)) throw new RuntimeException("密码输入错误！");

        return user;
    }
}
