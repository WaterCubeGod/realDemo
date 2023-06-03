package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.User;
import com.neu.group.service.UserService;

import net.sf.json.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 用户表现层方法
 */
@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    private static final String UPLOAD_DIR = "uploads";

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
    public R register(MultipartFile file,HttpServletRequest request) throws IOException,
            InvalidFormatException {
        // 获取上传文件的存储路径
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        // 获取文件数据
        String fileName = file.getOriginalFilename();

        // 创建上传目录
        File fileUploadDirectory = new File(uploadFilePath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }

        // 将文件保存到指定路径中
        String filePath = uploadFilePath + File.separator + fileName;
        assert fileName != null;
        file.transferTo(new File(fileUploadDirectory,fileName));

        boolean flag = userService.bulkImport(filePath);

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
        System.out.println(userService.countUser()%10);

        return new R(true,userService.countUser()/10 +
                (userService.countUser()%10 == 0 ? 0:1),"");
    }


}
