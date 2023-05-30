package com.neu.group.dao;

import com.neu.group.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * UserDao: 实体类User的映射
 */

@Mapper
public interface UserDao {

    //通过账号id和密码来查询某一行
    User selectByUsernameAndPassword(@Param("id") int id,@Param("password") String password);

    //插入一行
    int insertUser(@Param("username") String username, @Param("password") String password,
                   @Param("type") int type);

    //删除一行:必须知道对应的id和密码
    int deleteUser(@Param("id") int id,@Param("password") String password);

    //查找10行数据
    List<User> selectAllUser(int count);

}
