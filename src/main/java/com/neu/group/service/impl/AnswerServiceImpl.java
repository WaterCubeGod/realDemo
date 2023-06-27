package com.neu.group.service.impl;

import com.neu.group.dao.AnswerDao;
import com.neu.group.dao.QuestionnaireDao;
import com.neu.group.domain.*;
import com.neu.group.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerDao answerDao;
    @Autowired
    QuestionnaireDao questionnaireDao;
    @Transactional
    @Override
    public boolean addAnswer(List<Answer> answers) {
        boolean flag = true;
        int userId = answers.get(0).getUserId();
        answerDao.addAnswerId(userId);
        int aId = answerDao.selectAIdByUserId(userId);
        for (Answer answer:
             answers) {
            int qnId = answer.getQnId();
            int qId = answer.getqId();
            int type = answer.getType();
            //先新加answer_id
            flag = flag && answerDao.createAnswer(qnId,
                    answer.getQnName(),
                    type,
                    qId,
                    userId,
                    answer.getUserName(),
                    aId) > 0;
            int id = answerDao.selectId(qnId, qId, userId,aId);
            //根据题目类型更新表格
            switch (type){
                case 1:
                case 2:
                    //单双选
                    for (int item:
                         answer.getChoice()) {
                        answerDao.createAnswerChoice(id,item);
                    }
                    break;
                case 3:
                    //填空
                    answerDao.createAnswerBlank(id, answer.getContent());
                    break;
                case 4:
                    //矩阵
                    for (int i = 0; i < answer.getColumns().size(); i++) {
                        answerDao.createAnswerMatrix(id,answer.getChoice().get(i),
                                answer.getColumns().get(i));
                    }
                    break;
                case 5:
                    answerDao.createAnswerScale(id,answer.getChoice().get(0),
                            answer.getScore());
                    break;
                default:
                    System.out.println("未知的type：" + type);
                    break;
            }

        }
        return flag;
    }

    @Override
    public List<Answer> selectAllByQnId(int qnId,int aId) {
        List<Answer> answers = answerDao.selectAnswer(qnId, aId);
        get(answers);
        return answers;
    }

    @Override
    public List<Answer> selectByLink(String link,String userName ) {
//        Questionnaire questionnaire = questionnaireDao.selectByLink(link);
        return null;
    }
    @Override
    public List<Answer> selectAllQuestionnaireInProject(int projectId){
        List<Questionnaire> questionnaires = questionnaireDao.selectAll(projectId);
        List<Answer> representAns = new ArrayList<>();
        for (Questionnaire item:
             questionnaires) {

            List<Answer> answers = answerDao.selectAnswer2(item.getId());
            representAns.addAll(answers);

        }
        return representAns;
    }

    @Override
    public List<Answer> selectAllAnswer(int qnId){
        List<Answer> answers = answerDao.selectAllAnswerByQnId(qnId);
        get(answers);
        return answers;
    }
    //查找相同题目的答案
    @Override
    public List<Answer> selectAnswerInSameQuestion(List<Option> options,int qnId){
        List<Questionnaire> questionnaires = questionnaireDao.selectAll(
                questionnaireDao.selectProjectBelong(qnId));
        List<Answer> answers = new ArrayList<>();
        for (Option option:
             options) {
            for (Questionnaire questionnaire:
                 questionnaires) {
                answers.addAll( answerDao.selectSameAnswer(questionnaire.getId(),option.getqId() -1));
            }
        }
        return answers;
    }


    private void get(List<Answer> answers) {
        for (Answer answer:
             answers) {
            int type = answer.getType();
            List<SingleAnswer> singleAnswers = answerDao.selectAnswerOptionById(type,
                    answer.getId());
            switch (type){
                case 1:
                case 2:
                    List<Integer> listChoice = new ArrayList<>();
                    for (SingleAnswer singleAnswer:
                            singleAnswers) {
                        listChoice.add(singleAnswer.getChoice());
                    }
                    answer.setChoice(listChoice);
                    break;
                case 3:
                    //只有一个
                    answer.setContent(singleAnswers.get(0).getContent());
                    break;
                case 4:
                    List<Integer> choice = new ArrayList<>();
                    List<Integer> columns = new ArrayList<>();
                    for (SingleAnswer singleAnswer : singleAnswers) {
                        choice.add(singleAnswer.getChoice());
                        columns.add(singleAnswer.getColumns());
                    }
                    answer.setChoice(choice);
                    answer.setColumns(columns);
                    break;
                case 5:
                    answer.setChoice(Collections.singletonList(
                            singleAnswers.get(0).getChoice()));
                    answer.setScore(singleAnswers.get(0).getScore());
                    break;
                default:
                    System.out.println("未知的type：" + type);
                    break;
            }
        }
    }


}
