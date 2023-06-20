package com.neu.group.dao;

import com.neu.group.domain.Option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OptionDao {

    int addChoice(@Param("qnId") int qnId,
                  @Param("qId") int qId,
                  @Param("oId") int oId,
                  @Param("content") String content);

    int addMatrix(@Param("qnId") int qnId,
                  @Param("qId") int qId,
                  @Param("oId") int oId,
                  @Param("content") String content);

    int addMatrixColumn(@Param("qnId") int qnId,
                        @Param("qId") int qId,
                        @Param("oId") int oId,
                        @Param("content") String content);

    int addScale(@Param("qnId") int qnId,
                 @Param("qId") int qId,
                 @Param("oId") int oId,
                 @Param("content") String content,
                 @Param("score") int score);

    List<Option> selectAllChoice(@Param("qnId") int qnId,
                                 @Param("qId") int qId);
}
