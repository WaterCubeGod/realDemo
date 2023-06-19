package com.neu.group.dao;

import com.neu.group.domain.Questionnaire;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionnaireDao {
    int createQuestionnaire(@Param("name") String name,
                            @Param("description") String description,
                            @Param("project_belong") int projectBelong,
                            @Param("type") String type,
                            @Param("create_time") String createTime,
                            @Param("finish_time") String finishTime);

    List<Questionnaire> selectAll(@Param("project_belong") int projectBelong);
}