package com.neu.group.service.impl;

import com.neu.group.dao.OptionDao;
import com.neu.group.dao.QuestionDao;
import com.neu.group.domain.Option;
import com.neu.group.domain.Question;
import com.neu.group.domain.SingleOption;
import com.neu.group.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    OptionDao optionDao;

    @Transactional
    @Override
    public boolean addQuestion(List<Option> options) {
        boolean flag = true;


        for (Option option : options) {
            //添加题目
            flag = flag && questionDao.addQuestion(option.getQnId(),
                    option.getqId(),
                    option.getTitle(),
                    option.getReq(),
                    option.getType()) > 0;

            int type = option.getType();
            //根据题目类型添加
            switch (type) {
                case 1:
                case 2:
                    //添加选项
                    for (int i = 0; i < option.getContent().size(); i++) {
                        flag = flag && optionDao.addChoice(option.getQnId(),
                                option.getqId(),
                                i + 1,
                                option.getContent().get(i)) > 0;
                    }
                    break;
                case 4:
                    //添加矩阵行
                    for (int i = 0; i < option.getContent().size(); i++) {
                        flag = flag && optionDao.addMatrix(option.getQnId(),
                                option.getqId(),
                                i + 1,
                                option.getContent().get(i)) > 0;
                    }
                    //添加矩阵列
                    for (int i = 0; i < option.getColumns().size(); i++) {
                        flag = flag && optionDao.addMatrixColumn(option.getQnId(),
                                option.getqId(),
                                i + 1,
                                option.getColumns().get(i)) > 0;
                    }
                    break;
                case 5:
                    //添加量表项
                    for (int i = 0; i < option.getContent().size(); i++) {
                        flag = flag && optionDao.addScale(option.getQnId(),
                                option.getqId(),
                                i + 1,
                                option.getContent().get(i),
                                option.getScore().get(i)) > 0;
                    }
                    break;
                default:
                    System.out.println(type);
                    break;
            }
        }


        return flag;
    }

    @Transactional
    @Override
    public List<Option> selectAllByQnId(int qnId) {

        List<Question> questions = questionDao.selectAllByQnId(qnId);
        List<Option> options = new ArrayList<>();

        for (Question question : questions) {
            //根据题目类型添加
            switch (question.getType()) {
                case 1:
                case 2:
                    //添加选项
                    options.add(SPToP(optionDao.selectAllChoice(question.getQnId(),question.getqId()),
                            question));
                    break;
                case 4:
                    //添加矩阵行、列
                    Option optionM = SPToP(optionDao.selectAllMatrix(question.getQnId(),question.getqId()),
                            question);
                    Option optionC = SPToP(optionDao.selectAllMatrixColumn(question.getQnId(),question.getqId()),
                            question);
                    //合并为一个选项集合
                    List<String> columns = optionC.getColumns();
                    optionM.setColumns(columns);

                    options.add(optionM);
                    break;
                case 5:
                    //添加量表项
                    options.add(SPToP(optionDao.selectAllScale(question.getQnId(),question.getqId()),
                            question));
                    break;
            }
        }

        return options;
    }
    //将单个选项集合为题目
    private Option SPToP(List<SingleOption> singleOptions, Question question) {
        int qnId = question.getQnId();
        int qId = question.getqId();
        String title = question.getTitle();
        int req = question.getReq();
        int type = question.getType();

        List<String> contents = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();

        for (SingleOption singleOption : singleOptions) {
            contents.add(singleOption.getContent());
            columns.add(singleOption.getColumn());
            scores.add(singleOption.getScore());
        }

        return new Option(qnId,qId,title,req,type,contents,columns,scores);
    }
}
