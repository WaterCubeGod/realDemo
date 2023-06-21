package com.neu.group.service.impl;


import com.neu.group.dao.ProjectDao;
import com.neu.group.dao.QuestionnaireDao;
import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    QuestionnaireDao questionnaireDao;
    @Autowired
    ProjectDao projectDao;

    @Transactional
    @Override
    public boolean addQuestionnaire(String name, String description, int projectBelong, String type, String createTime, String finishTime) {
        return questionnaireDao.createQuestionnaire(name,description,projectBelong,type,createTime,finishTime) > 0;
    }

    @Transactional
    @Override
    public List<Questionnaire> selectAll(int projectBelong) {


        return questionnaireDao.selectAll(projectBelong);
    }

    @Override
    public Questionnaire selectQn(int projectBelong, String name) {
        return questionnaireDao.selectQn(projectBelong, name);
    }
}
