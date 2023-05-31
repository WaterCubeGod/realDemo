package com.neu.group.service;

import com.neu.group.domain.User;

import java.util.List;

/**
 * UserService: User的服务层接口
 */
public interface UserService {

    //登录接口
    User login(String username, String password);

    //注册接口
    boolean register(String username, String password, int type);

    //注销接口
    boolean logout(int id, String password);

    //分页查询接口
    List<User> selectUserDividerByPage(int count);

    //用户总数
    Integer countUser();

}
