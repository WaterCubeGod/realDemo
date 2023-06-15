package com.neu.group;

import com.neu.group.domain.User;
import com.neu.group.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @Test
    void userLogin(){
        User user = userService.login("熊梓详", "123456");
        System.out.println(user);
    }
    /*
      测试用
     */
    @Test
    void Test(){
        User user = new User(1,"111","123",1);
        int id = user.getId();
        String password = user.getPassword();
        int type = user.getType();
        String username = user.getUsername();
    }
}
