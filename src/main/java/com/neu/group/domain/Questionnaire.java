package com.neu.group.domain;

import org.apache.ibatis.type.Alias;

@Alias("Questionnaire")
public class Questionnaire {
    int id;
    String name;
    String description;
    String projectBelong;
    String type;
    String createTime;
    String finishTime;

    public Questionnaire() {
    }

    public Questionnaire(int id, String name, String description, String projectBelong, String type, String createTime, String finishTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectBelong = projectBelong;
        this.type = type;
        this.createTime = createTime;
        this.finishTime = finishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectBelong() {
        return projectBelong;
    }

    public void setProjectBelong(String projectBelong) {
        this.projectBelong = projectBelong;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectBelong='" + projectBelong + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
