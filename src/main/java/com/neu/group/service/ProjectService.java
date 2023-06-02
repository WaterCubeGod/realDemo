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

    boolean updateProject(String projectName, String projectDescription, int projectId);

    boolean deleteProject(int projectId);

    List<Project> searchProjectByName(int userId, String projectName);

}
