package com.neu.group.domain;

import java.io.Serializable;

public class UserDelete implements Serializable {
    private static final long serialVersionUID = -1;
    int id;
    String password;

    public UserDelete() {

    }

    public UserDelete(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDelete{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
