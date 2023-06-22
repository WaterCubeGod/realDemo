package com.neu.group.service;

import com.neu.group.domain.Option;
import com.neu.group.domain.Question;

import java.util.List;

public interface QuestionService {

    public boolean addQuestion(List<Option> options);

    public List<Option> selectAllByQnId(int qnId);

    public List<Option> selectByLink(String link);

}
