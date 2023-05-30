package com.neu.group;

import com.neu.group.domain.User;
import com.neu.group.service.UserService;
import com.neu.group.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @Test
    void userLogin(){
        User user = userService.login(1, "123456");
        System.out.println(user);
    }

}
