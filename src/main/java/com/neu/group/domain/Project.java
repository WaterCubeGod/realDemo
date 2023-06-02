package com.neu.group.domain;

import org.apache.ibatis.type.Alias;

/**
 * 工程实体类
 */
@Alias("Project")
public class Project {
    private int projectId;
    private int userId;
    private String projectName;
    private String projectDescription;
    private String createTime;

    public Project(){

    }

    public Project(int projectId, int userId, String projectName, String projectDescription, String createTime) {
        this.projectId = projectId;
        this.userId = userId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createTime = createTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
