package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表现层方法
 */
@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @GetMapping("/{username}/{password}")
    public R login(@PathVariable String username, @PathVariable String password){
        User user = userService.login(username,password);

        return new R(true, user, user == null ? "账号或密码错误" : "");
    }

    //用户注册
    @PutMapping
    public R register(@RequestBody User user){
        Boolean flag = userService.register(user.getUsername(),user.getPassword(), user.getType());

        return new R(flag, "", flag ? "添加成功" : "添加失败");
    }

    //注销用户
    @DeleteMapping("/{id}/{password}")
    public R logout(@PathVariable int id, @PathVariable String password){
        Boolean flag = userService.logout(id,password);

        return new R(flag, "", flag ? "删除成功" : "删除失败");
    }

    //分页查询用户
    @GetMapping("/{count}")
    public R selectUserDividerByPage(@PathVariable int count){
        return new R(true,userService.selectUserDividerByPage(count),"");
    }

    //用户数量统计
    @GetMapping
    public R countUser(){
        return new R(true,userService.countUser()/10 + 1,"");
    }

}
