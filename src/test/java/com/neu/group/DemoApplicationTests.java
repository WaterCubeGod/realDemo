package com.neu.group;


import com.neu.group.domain.User;
import com.neu.group.service.UserService;


import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@SpringBootTest
class DemoApplicationTests {
//    @Test
//    void contextLoads() {

//    }
    Logger log = Logger.getLogger(DemoApplicationTests.class);
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @Rollback
    public void queryUserList(){

        //调用userMapper的方法
        List<User> list = userService.selectAllUser();
        int count = userService.countUser();
    }

    @Test
    @Transactional
    @Rollback
    public void selectUserInfo(){

        //调用userMapper的方法
        User user = userService.login("熊梓详","123456");
        if(user == null){
            // 记录error级别的信息
        }else{
            System.out.println(user);
            // 记录info级别的信息
            log.info(">>qselectUserInfo用户登录测试成功");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void insert(){
        //调用userMapper的方法
        boolean flag = userService.register("一边啊1212","123456",0);
        if(!flag){
            // 记录error级别的信息
        }else{
            System.out.println(flag);
            // 记录info级别的信息
            log.info(">>insert用户插入测试成功");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void deleteUserByName(){
        //调用userMapper的方法
        boolean flag = userService.logout(58,"123456");
        if(!flag){
            // 记录error级别的信息
        }else{
            System.out.println(flag);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void editUserById(){
        //调用userMapper的方法
        boolean flag = userService.editUser(48, "两边两", "123456");
        if(!flag){
            // 记录error级别的信息
        }else{
            System.out.println(flag);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }

    @Test
    @Transactional
    @Rollback
    public void selectUserByUsername(){
        //调用userMapper的方法
        List<User> users = userService.selectUserByUsername( "一边",0);
        if(users == null){
            // 记录error级别的信息
        }else{
            System.out.println(users);
        }
    }


}
