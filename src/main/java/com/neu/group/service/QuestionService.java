package com.neu.group.service;

import com.neu.group.domain.Option;
import com.neu.group.domain.Question;

import java.util.List;

public interface QuestionService {

    public boolean addQuestion(List<Option> options);

    public List<Question> selectAllByQnId(int qnId);



}