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
                  @Param("content") String content);

    List<Option> selectAllByQId(@Param("qnId") int qnId,
                                @Param("qId") int qId);
}
