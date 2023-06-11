package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 用户表现层方法
 */
@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    Logger log = Logger.getLogger(UserController.class);

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

    //批量导入用户
    @PutMapping("/all")
    public R register(MultipartFile multiFile) throws IOException {
        String fileName = multiFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        boolean flag = false;
        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            flag = userService.bulkImport(file);
        } catch (Exception e) {
            log.error(">>文件类型转换错误");

        } finally {
            // 操作完上面的文件 需要删除在根目录下生成的临时文件
            assert file != null;
            File f = new File(file.toURI());
            Files.delete(f.toPath());
            file.deleteOnExit();
        }

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

        return new R(true,userService.selectUserByUsername(username,count),"");
    }

    //用户数量统计
    @GetMapping
    public R countUser(){
        return new R(true,userService.countUser()/10 +
                (userService.countUser()%10 == 0 ? 0:1),"");
    }

    //导出用户excel
    @PostMapping("/export")
    public R exportUserExcel() {

        return new R(true,userService.selectAllUser(),"");

    }


}
