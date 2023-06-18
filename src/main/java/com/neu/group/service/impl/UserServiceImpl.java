package com.neu.group.service.impl;

import cc.aicode.e2e.ExcelHelper;
import cc.aicode.e2e.exception.ExcelContentInvalidException;
import cc.aicode.e2e.exception.ExcelParseException;
import cc.aicode.e2e.exception.ExcelRegexpValidFailedException;

import com.neu.group.dao.UserDao;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * UserServiceImpl: UserService接口的实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    Logger log = Logger.getLogger(UserServiceImpl.class);
    //用户登录
    @Override
    @Transactional
    public User login(String username, String password) {

        return userDao.selectByUsernameAndPassword(username,password);
    }

    //用户注册
    @Override
    @Transactional
    public boolean register(String username, String password, int type) {

        int count = userDao.insertUser(username,password,type);

        return count > 0;
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
    @Transactional
    public boolean logout(int id, String password) {

        int count = userDao.deleteUser(id,password);

        return count > 0;
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
