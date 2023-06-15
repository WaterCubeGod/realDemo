package com.neu.group.service.impl;


import com.neu.group.dao.QuestionnaireDao;
import com.neu.group.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    QuestionnaireDao questionnaireDao;

    @Transactional
    @Override
    public boolean addQuestionnaire(String name, String description, String projectBelong, String type, String createTime, String finishTime) {
        return questionnaireDao.createQuestionnaire(name,description,projectBelong,type,createTime,finishTime) > 0;
    }
}
