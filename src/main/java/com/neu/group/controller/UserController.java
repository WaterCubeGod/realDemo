package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;

import net.sf.json.JSONObject;
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
        boolean flag = userService.register(user.getUsername(),user.getPassword(), user.getType());

        return new R(flag, "", flag ? "添加成功" : "添加失败,用户名重复");
    }

    //注销用户
    @DeleteMapping("/{id}/{password}")
    public R logout(@PathVariable int id, @PathVariable String password){
        boolean flag = userService.logout(id,password);

        return new R(flag, "", flag ? "删除成功" : "删除失败");
    }

    @GetMapping("/{id}/{username}/{password}")
    public R editUser(@PathVariable int id, @PathVariable String username,
                      @PathVariable String password){
        boolean flag = userService.editUser(id,username,password);

        return new R(flag, "", flag ? "编辑成功" : "编辑失败,可能有重复用户名");
    }

    //分页查询用户
    @PostMapping
    public R selectUserDividerByPage(@RequestBody JSONObject jsonObject){
        int count = jsonObject.getInt("count");
        String username = jsonObject.getString("username");

        if(username == null){
            return new R(true,userService.selectUserDividerByPage(count),"");
        }
        return new R(true,userService.selectUserByUsername(username,count),"");
    }

    //用户数量统计
    @GetMapping
    public R countUser(){
        return new R(true,userService.countUser()/10 + 1,"");
    }


}
