package com.neu.group.domain;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

/**
 * User: 用户实体类
 */
//对于实体类取别名，使得xml文件能正确找到对应的实体类
@Alias("User")
public class User {
    private int id;
    private String username;
    private String password;
    private int type;

    public User() {
    }

    public User(int id, String username, String password, int type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
