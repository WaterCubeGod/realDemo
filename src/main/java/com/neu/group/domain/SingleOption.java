package com.neu.group.domain;

public class SingleOption extends Question{
    private String content;
    private String columns;
    private int score;

    public SingleOption() {

    }

    public SingleOption(String content, String columns, int score) {
        this.content = content;
        this.columns = columns;
        this.score = score;
    }

    public SingleOption(int qnId, int qId, String title, int req, int type, String content, String columns, int score) {
        super(qnId, qId, title, req, type);
        this.content = content;
        this.columns = columns;
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColumn() {
        return columns;
    }

    public void setColumn(String column) {
        this.columns = column;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SingleOption{" +
                "content='" + content + '\'' +
                ", column='" + columns + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

}
