package com.neu.group.dao;

import com.neu.group.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionDao {

    int addQuestion(@Param("qnId") int qnId,
                    @Param("qId") int qId,
                    @Param("title") String title,
                    @Param("req") int req,
                    @Param("type") int type);

    List<Question> selectAllByQnId(@Param("qnId") int qnId);

}
