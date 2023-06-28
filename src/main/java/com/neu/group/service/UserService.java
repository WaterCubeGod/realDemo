package com.neu.group.service;

import com.neu.group.domain.User;
import com.neu.group.domain.UserDelete;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.cache.annotation.Cacheable;

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
    User register(User user);

    //批量及加入接口
    boolean bulkImport(File file) throws IOException, InvalidFormatException;

    //用户注销
    UserDelete logout(UserDelete userDelete);

    //编辑接口
    boolean editUser(int id, String username, String password);

    //分页查询接口
    List<User> selectAllUser();

    //根据用户名查询
    List<User> selectUserByUsername(String username, int count);

    //用户总数
    Integer countUser();

    void a();

}
