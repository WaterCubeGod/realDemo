package com.neu.group.controller.utils;

/**
 * 用于前端和后端格式统一
 */

public class R {
    private Boolean flag;
    private Object data;
    private String message;

    public R(){

    }

    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, Object data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "R{" +
                "flag=" + flag +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
