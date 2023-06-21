package com.neu.group.dao;

import com.neu.group.domain.Option;
import com.neu.group.domain.SingleOption;
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

    List<SingleOption> selectAllChoice(@Param("qnId") int qnId,
                                       @Param("qId") int qId);

    List<SingleOption> selectAllMatrix(@Param("qnId") int qnId,
                                 @Param("qId") int qId);

    List<SingleOption> selectAllMatrixColumn(@Param("qnId") int qnId,
                                 @Param("qId") int qId);

    List<SingleOption> selectAllScale(@Param("qnId") int qnId,
                                 @Param("qId") int qId);
}
