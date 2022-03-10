package com.springboot.ems.mapper;

import com.springboot.ems.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface UserMapper {

    //根据用户名查询用户
    User findByUserName (String username);
    int countByUserName (String username);
    //保存用户信息
    void save(User user);
}
