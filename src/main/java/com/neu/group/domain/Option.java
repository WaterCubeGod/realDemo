package com.neu.group.domain;


import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Option")
public class Option extends Question{

    private List<String> content;

    private List<String> columns;

    private List<Integer> score;

    public Option() {
    }

    public Option(List<String> content, List<String> columns, List<Integer> score) {
        this.content = content;
        this.columns = columns;
        this.score = score;
    }

    /**
     *
     * @param qnId:问卷id
     * @param qId:题目id
     * @param title:标题
     * @param req:是否必答
     * @param type:类型
     * @param content:行选项
     * @param columns:列选项
     * @param score:列分数
     */
    public Option(int qnId, int qId, String title, int req, int type,
                  List<String> content, List<String> columns, List<Integer> score) {
        super(qnId, qId, title, req, type);
        this.content = content;
        this.columns = columns;
        this.score = score;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
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
                ", columns=" + columns +
                ", score=" + score +
                '}';
    }
}
