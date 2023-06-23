package com.neu.group.service.impl;


import com.neu.group.dao.ProjectDao;
import com.neu.group.dao.QuestionnaireDao;
import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public boolean updateLink(String link, int id) {
        return questionnaireDao.updateLink(link, id) > 0;
    }

    @Override
    public Questionnaire selectByLink(String link) {
        return questionnaireDao.selectByLink(link);
    }

    @Override
    public Questionnaire selectById(int id) {
        return questionnaireDao.selectById(id);
    }

    @Override
    public boolean deleteQn(int id) {
        Questionnaire questionnaire = questionnaireDao.selectById(id);
        if(isBelongPeriodTime(questionnaire.getCreateTime(),questionnaire.getFinishTime())) {
            return false;
        }
        return questionnaireDao.deleteQn(id) > 0;
    }

    /*
     *  判断当前时间是否在设置的dark mode时间段内
     *  @param date1: 开始时间（hh:mm）
     *  @param date2: 结束时间（hh:mm）
     */
    private boolean isBelongPeriodTime(String date1, String date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date(System.currentTimeMillis());
        Date startTimeDate;
        Date endTimeDate;
        Calendar date = Calendar.getInstance();
        Calendar begin = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            date.setTime(df.parse(df.format(currentTime)));
            startTimeDate = df.parse(date1);
            endTimeDate = df.parse(date2);
            begin.setTime(startTimeDate);
            end.setTime(endTimeDate);

            // 修改比较逻辑，包括日期部分
            if (endTimeDate.before(startTimeDate)) {
                return date.after(begin) && date.before(end);
            } else if (endTimeDate.equals(startTimeDate)) {
                return date.equals(begin) && date.equals(end) || (date.after(begin) && date.before(end));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 这里是时间段的起止都在同一天的情况，只需要判断当前时间是否在这个时间段内即可
        return date.after(begin) && date.before(end);
    }


}
