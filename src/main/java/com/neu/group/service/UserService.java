package com.neu.group.service;

import com.neu.group.domain.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * UserService: User的服务层接口
 */
public interface UserService {

    //登录接口
    User login(String username, String password);

    //注册接口
    boolean register(String username, String password, int type);

    //批量及加入接口
    boolean bulkImport(File file) throws IOException, InvalidFormatException;

    //注销接口
    boolean logout(int id, String password);

    //编辑接口
    boolean editUser(int id, String username, String password);

    //分页查询接口
    List<User> selectAllUser();

    //根据用户名查询
    List<User> selectUserByUsername(String username, int count);

    //用户总数
    Integer countUser();

}
