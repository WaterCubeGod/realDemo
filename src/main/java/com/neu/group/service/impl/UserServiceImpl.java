package com.neu.group.service.impl;

import cc.aicode.e2e.ExcelHelper;

import com.neu.group.dao.UserDao;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * UserServiceImpl: UserService接口的实现
 */
@Service
@CacheConfig(cacheNames = "user",keyGenerator = "keyGenerator")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    Logger log = Logger.getLogger(UserServiceImpl.class);
    //用户登录
    @Override
    @Transactional
    public User login(String username, String password) {

        return userDao.selectByUsernameAndPassword(username,password);
    }

    //用户注册
    @Override
    @Cacheable()
    public User register(User user) {
//        redisTemplate.opsForValue().set(user.getId()+"",user);
        return user;
    }

    //批量导入
    @Override
    @Transactional
    public boolean bulkImport(File file) throws IOException, InvalidFormatException {
        boolean flag = false;

        ExcelHelper eh = ExcelHelper.readExcel(file);
        List<User> users;
        try {
            users = eh.toEntitys(User.class);
            flag = true;
            for (User user : users) {
                int count = userDao.insertUser(user.getUsername(),user.getPassword(), user.getType());
                flag = (count > 0) && flag;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }

    //用户注销
    @Override
    @Cacheable()
    public void logout(String... key) {
        Set<Object> keys = redisTemplate.keys("*");
        User user = (User) redisTemplate.opsForValue().get("12312");
        System.out.println(user);
//        if (key != null && key.length > 0) {
//            if (key.length == 1) {
//                redisTemplate.delete(key[0]);
//            } else {
//                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
//            }
//        }

    }

    //用户编辑
    @Override
    @Transactional
    public boolean editUser(int id, String username, String password) {

        int count = userDao.updateUser(id,username,password);

        return count > 0;
    }

    //分页查询用户
    @Override
    @Transactional
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    //查询
    @Override
    @Transactional
    public List<User> selectUserByUsername(String username, int count){
        return userDao.selectByUsername(username,count);
    }

    //返回用户数量
    @Override
    @Transactional
    public Integer countUser() {
        return userDao.countUser();
    }
}
