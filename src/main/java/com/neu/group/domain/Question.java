package com.neu.group.domain;

import org.apache.ibatis.type.Alias;

@Alias("Question")
public class Question {
    private int qnId;
    private int qId;
    private String title;
    private int req;
    private int type;

    public Question() {
    }

    public Question(int qnId, int qId, String title, int req, int type) {
        this.qnId = qnId;
        this.qId = qId;
        this.title = title;
        this.req = req;
        this.type = type;
    }

    public int getQnId() {
        return qnId;
    }

    public void setQnId(int qnId) {
        this.qnId = qnId;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReq() {
        return req;
    }

    public void setReq(int req) {
        this.req = req;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qnId=" + qnId +
                ", qId=" + qId +
                ", title='" + title + '\'' +
                ", req=" + req +
                ", type=" + type +
                '}';
    }
}
