package com.neu.group.service;

import com.neu.group.domain.Project;

import java.util.List;

/**
 * Project的服务层接口
 */
public interface ProjectService {

    //根据用户查看工程
    List<Project> selectProjectByUser(int userId);

    //增加工程
    boolean addProject(String projectName, String projectDescription, int userId);

    //更新工程信息
    boolean updateProject(String projectName, String projectDescription, int projectId);

    //删除工程
    boolean deleteProject(int projectId);

    //通过名称查找工程
    List<Project> searchProjectByName(int userId, String projectName);

}
