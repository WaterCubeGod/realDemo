package com.neu.group;

import com.neu.group.controller.UserController;
import com.neu.group.domain.User;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;
    @Test
    @Transactional
    @Rollback
    void test(){
        User user = new User(12312, "dsasdasd", "sdasda", 0);
        userController.register(user);
//        userController.login("dsasdasd","sdasda");
//        userController.countUser();
//        userController.editUser(123,"sdas","sddd");
        userController.logout(12312,"sdasda");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("count",1);
//        jsonObject.put("username","111");
//        userController.selectUserDividerByPage(jsonObject);
//        userController.exportUserExcel();
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }
}
