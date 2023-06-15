package com.neu.group.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireDao {
    int createQuestionnaire(@Param("name") String name,
                            @Param("description") String description,
                            @Param("project_belong") String projectBelong,
                            @Param("type") String type,
                            @Param("create_time") String createTime,
                            @Param("finish_time") String finishTime);
}
