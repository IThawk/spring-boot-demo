package com.ithawk.learn.springboot.service.impl;

import com.ithawk.learn.springboot.entity.ShareEmailDetail;
import com.ithawk.learn.springboot.entity.User;
import com.ithawk.learn.springboot.mapper.UserMapper;
import com.ithawk.learn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author IThawk
 * @version V1.0
 * @description:
 * @date 2020-04-11 14:48
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> getData(ShareEmailDetail email) {
        System.out.println("这个是User");
        return userMapper.selectAllUser();
    }

    @Override
    public List<String> getColumn() {
        return User.USER_COLUMN;
    }

    @Override
    public void addData(List<User> o) {
        //
        System.out.println("user.........");
        System.out.println(o);
    }
}
