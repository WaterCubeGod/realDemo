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

    int createAnswer(@Param("qnId") int qnId,
                     @Param("qnName") String qnName,
                     @Param("type") int type,
                     @Param("qId") int qId,
                     @Param("userId") int userId,
                     @Param("userName") String userName,
                     @Param("aId") int aId
                     );

    int createAnswerChoice(@Param("id") int id,
                     @Param("choice") int choice);

    int createAnswerMatrix(@Param("id") int id,
                           @Param("choice") int choice,
                           @Param("columns") int columns);

    int createAnswerBlank(@Param("id") int id,
                           @Param("content") String content);

    int createAnswerScale(@Param("id") int id,
                          @Param("choice") int choice,
                          @Param("score") int score);

    List<Answer> selectAnswer(@Param("qnId") int qnId,
                              @Param("aId") int aId);

    List<SingleAnswer> selectAnswerOptionById(@Param("type") int type,
                                              @Param("id") int id);
    int selectId(@Param("qnId") int qnId,
                 @Param("qId") int qId,
                 @Param("userId") int userId,@Param("aId") int aId);

    int addAnswerId(@Param("userId") int userId);

    int selectAIdByUserId(@Param("userId") int userId);

    List<Answer> selectAnswer2(@Param("qnId") int qnId);

    List<Answer> selectAllAnswerByQnId(@Param("qnId") int qnId);

    List<Answer> selectSameAnswer(@Param("qnId") int qnId,
                                  @Param("qId") int qId);
}
