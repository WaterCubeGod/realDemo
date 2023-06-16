package com.neu.group;

import com.neu.group.dao.UserDao;
import com.neu.group.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RealDemoApplicationTests {


    @Autowired
    private UserDao userDao;

    @Test
    void login(){
        User user = userDao.selectByUsernameAndPassword("熊梓详","123456");
        System.out.println(user);
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }

    @Test
    void selectAllUser(){
        List<User> users = userDao.selectAllUser();
        System.out.println(users);
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }
}
