package com.neu.group.service.impl;

import com.neu.group.dao.UserDao;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserServiceImpl: UserService接口的实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //用户登录
    @Override
    @Transactional
    public User login(int id, String password) {

        return userDao.selectByUsernameAndPassword(id,password);
    }

    //用户注册
    @Override
    @Transactional
    public boolean register(String username, String password, int type) {

        int count = userDao.insertUser(username,password,type);

        return count > 0;
    }

    //用户注销
    @Override
    @Transactional
    public boolean logout(int id, String password) {

        int count = userDao.deleteUser(id,password);

        return count > 0;
    }

    //分页查询用户
    @Override
    @Transactional
    public List<User> selectUserDividerByPage(int count) {

        return userDao.selectAllUser(count);
    }
}
