package com.neu.group.domain;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Answer")
public class Answer {
    private int id;
    private int qnId;
    private int qId;
    private int userId;
    private String createTime;
    private String content;
    private List<Integer> choice;
    private List<Integer> columns;
    private int score;

    public Answer() {
    }

    public Answer(int id, int qnId, int qId, int userId, String createTime,
                  String content, List<Integer> choice, List<Integer> columns, int score) {
        this.id = id;
        this.qnId = qnId;
        this.qId = qId;
        this.userId = userId;
        this.createTime = createTime;
        this.content = content;
        this.choice = choice;
        this.columns = columns;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getChoice() {
        return choice;
    }

    public void setChoice(List<Integer> choice) {
        this.choice = choice;
    }

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", qnId=" + qnId +
                ", qId=" + qId +
                ", userId=" + userId +
                ", createTime='" + createTime + '\'' +
                ", content='" + content + '\'' +
                ", choice=" + choice +
                ", columns=" + columns +
                ", score=" + score +
                '}';
    }
}