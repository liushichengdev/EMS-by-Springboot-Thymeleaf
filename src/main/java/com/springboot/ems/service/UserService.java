package com.springboot.ems.service;

import com.springboot.ems.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    //注册用户
    void register(User user);

    //用户登录
    User login(String username, String password);
}
