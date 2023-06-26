package com.neu.group.domain;

public class SingleAnswer {
    private int id;
    private String content;
    private int choice;
    private int columns;
    private int score;

    public SingleAnswer() {
    }

    public SingleAnswer(int id, String content, int choice, int columns, int score) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
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
        return "SingleAnswer{" +
                "id=" + id +
                ", content=" + content +
                ", choice=" + choice +
                ", columns=" + columns +
                ", score=" + score +
                '}';
    }
}
