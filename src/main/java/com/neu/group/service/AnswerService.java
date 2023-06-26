package com.neu.group.service;

import com.neu.group.domain.Answer;

import java.util.List;

public interface AnswerService {
    public boolean addAnswer(List<Answer> answers);

    public List<Answer> selectAllByQnId(int qnId,String userName);

    public List<Answer> selectByLink(String link,String userName);
}
