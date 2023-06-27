package com.neu.group.service;

import com.neu.group.domain.Answer;
import com.neu.group.domain.Option;

import java.util.List;

public interface AnswerService {
    public boolean addAnswer(List<Answer> answers);

    public List<Answer> selectAllByQnId(int qnId,int aId);

    public List<Answer> selectByLink(String link,String userName);


    public List<Answer> selectAllQuestionnaireInProject(int projectId);

    public List<Answer> selectAllAnswer(int qnId);


    //查找相同题目的答案
    List<Answer> selectAnswerInSameQuestion(List<Option> options, int qnId);
}
