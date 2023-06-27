package com.neu.group.dao;


import com.neu.group.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 工程实体类Project的映射
 */
@Repository
@Mapper
public interface ProjectDao {

    //根据用户id查找project
    List<Project> selectProjectByUser(@Param("userId") int userId);

    //新加一行
    int insertProject(@Param("projectName") String projectName,
                      @Param("projectDescription") String projectDescription,
                      @Param("userId") int userId);

    int updateProject(@Param("projectName") String projectName,
                      @Param("projectDescription") String projectDescription,
                      @Param("projectId") int projectId);

    int deleteProject(@Param("projectId") int projectId);

    List<Project> selectProjectByProjectName(@Param("userId") int userId, @Param("projectName") String projectName);

    Project selectByProjectName(@Param("userId") int userId, @Param("projectName") String projectName);


}
