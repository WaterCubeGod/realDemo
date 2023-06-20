package com.neu.group.domain;


import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Option")
public class Option extends Question{

    private List<String> content;

    private List<String> column;

    private List<Integer> score;

    public Option() {
    }

    /**
     *
     * @param qnId:问卷id
     * @param qId:题目id
     * @param title:标题
     * @param req:是否必答
     * @param type:类型
     * @param content:行选项
     * @param column:列选项
     * @param score:列分数
     */
    public Option(int qnId, int qId, String title, int req, int type,
                  List<String> content, List<String> column, List<Integer> score) {
        super(qnId, qId, title, req, type);
        this.content = content;
        this.column = column;
        this.score = score;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    public List<Integer> getScore() {
        return score;
    }

    public void setScore(List<Integer> score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Option{" +
                "content=" + content +
                ", column=" + column +
                ", score=" + score +
                '}';
    }
}
