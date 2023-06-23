package com.neu.group.domain;

import org.apache.ibatis.type.Alias;

@Alias("Questionnaire")
public class Questionnaire {
    private int id;
    private String name;
    private String description;
    private int projectBelong;
    private String type;
    private String createTime;
    private String finishTime;
    private String link;

    int isDelete;

    public Questionnaire() {
    }

    public Questionnaire(int id, String name, String description, int projectBelong,
                         String type, String createTime, String finishTime, String link, int isDelete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectBelong = projectBelong;
        this.type = type;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.link = link;
        this.isDelete = isDelete;
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

    public int getProjectBelong() {
        return projectBelong;
    }

    public void setProjectBelong(int projectBelong) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectBelong=" + projectBelong +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
