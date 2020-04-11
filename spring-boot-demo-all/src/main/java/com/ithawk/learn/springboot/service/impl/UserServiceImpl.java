package com.ithawk.learn.springboot.service.impl;

import com.ithawk.learn.springboot.entity.User;
import com.ithawk.learn.springboot.mapper.UserMapper;
import com.ithawk.learn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author IThawk
 * @version V1.0
 * @description:
 * @date 2020-04-11 14:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.saveUser(user);
    }
}
