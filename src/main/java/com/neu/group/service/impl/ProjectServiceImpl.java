package com.neu.group.service.impl;

import com.neu.group.dao.ProjectDao;
import com.neu.group.domain.Project;
import com.neu.group.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    @Transactional
    public List<Project> selectProjectByUser(int userId) {
        return projectDao.selectProjectByUser(userId);
    }

    @Override
    @Transactional
    public boolean addProject(String projectName, String projectDescription, int userId) {
        return projectDao.insertProject(projectName, projectDescription, userId) > 0;
    }

    @Override
    @Transactional
    public boolean updateProject(String projectName, String projectDescription, int projectId) {
        return projectDao.updateProject(projectName, projectDescription, projectId) > 0;
    }

    @Override
    @Transactional
    public boolean deleteProject(int projectId) {
        return projectDao.deleteProject(projectId) > 0;
    }

    @Override
    @Transactional
    public List<Project> searchProjectByName(int userId, String projectName) {
        return projectDao.selectProjectByProjectName(userId,projectName);
    }
}
