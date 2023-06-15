package com.neu.group.service;

import org.apache.ibatis.annotations.Param;

public interface QuestionnaireService {
    boolean addQuestionnaire(@Param("name") String name,
                             @Param("description") String description,
                             @Param("project_belong") String projectBelong,
                             @Param("type") String type,
                             @Param("create_time") String createTime,
                             @Param("finish_time") String finishTime);

}
