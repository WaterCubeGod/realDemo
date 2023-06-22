package com.neu.group.dao;

import com.neu.group.domain.Answer;
import com.neu.group.domain.SingleAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnswerDao {

    int createAnswer(@Param("id") int id,
                     @Param("qnId") int qnId,
                     @Param("qnName") int qnName,
                     @Param("qId") int qId,
                     @Param("userId") int userId,
                     @Param("userName") int userName,
                     @Param("createTime") String createTime);

    int createAnswerChoice(@Param("id") int id,
                     @Param("choice") int choice);

    int createAnswerMatrix(@Param("id") int id,
                           @Param("choice") int choice,
                           @Param("columns") int columns);

    int createAnswerBlank(@Param("id") int id,
                           @Param("content") String content);

    int createAnswerScale(@Param("id") int id,
                          @Param("content") String content,
                          @Param("score") int score);

    List<Answer> selectAnswer(@Param("qnId") int qnId,
                              @Param("userId") int userId);

    List<SingleAnswer> selectAnswerOptionById(@Param("type") int type,
                                              @Param("id") int id);
}
