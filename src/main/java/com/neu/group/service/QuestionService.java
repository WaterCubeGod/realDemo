package com.neu.group.service;

import com.neu.group.domain.Option;
import com.neu.group.domain.Questionnaire;

import java.util.List;

public interface QuestionService {

    public boolean addQuestion(List<Option> options);

    public List<Option> selectAllByQnId(int qnId);

    public List<Option> selectByLink(String link);

    public Questionnaire selectQuestionnaire(String link);


    List<Option> selectSameQuestionInProject(int qId, int qnId);
}
