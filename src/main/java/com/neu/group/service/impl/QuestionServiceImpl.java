package com.neu.group.service.impl;

import com.neu.group.dao.OptionDao;
import com.neu.group.dao.QuestionDao;
import com.neu.group.domain.Option;
import com.neu.group.domain.Question;
import com.neu.group.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

            //根据题目类型添加
            switch (option.getType()) {
                case 1 | 2:
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
                    for (int i = 0; i < option.getColumn().size(); i++) {
                        flag = flag && optionDao.addMatrixColumn(option.getQnId(),
                                option.getqId(),
                                i + 1,
                                option.getContent().get(i)) > 0;
                    }
                    break;
                case 5:
                    //添加量表项
                    for (int i = 0; i < option.getColumn().size(); i++) {
                        flag = flag && optionDao.addScale(option.getQnId(),
                                option.getqId(),
                                i + 1,
                                option.getContent().get(i),
                                option.getScore().get(i)) > 0;
                    }
                    break;
                default:
                    break;
            }
        }


        return flag;
    }

    @Transactional
    @Override
    public List<Question> selectAllByQnId(int qnId) {
        return questionDao.selectAllByQnId(qnId);
    }
}
