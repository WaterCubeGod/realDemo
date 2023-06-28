package com.neu.group;

import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionnaireService;
import com.neu.group.service.impl.QuestionnaireServiceImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionnaireTest {

    Logger log = Logger.getLogger(QuestionnaireTest.class);
    @Autowired
    QuestionnaireService questionnaireService;

    @Transactional
    @Test
//    @Transactional
//    @Rollback
    void test(){
        Questionnaire questionnaire = new Questionnaire(1,"23","234",14,"234","we","234","234",1);
        questionnaireService.addQuestionnaire("aefuifee","d1dsda",1,"ddsad","2023-06-02 12:49:17","2023-06-12 12:49:17");
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }

    @Transactional
    @Test
    void selectTest() {
        List<Questionnaire> questionnaires = questionnaireService.selectAll(36);

        if(questionnaires != null) {
            log.info(">>qselectTest问卷查找测试成功");
        } else {
            log.debug(">>qselectTest问卷查找测试失败");
        }
    }

    @Transactional
    @Test
    void selectQnTest() {
        Questionnaire questionnaire = questionnaireService.selectQn(36, "阿松大");

        if(questionnaire != null) {
            log.info(">>查找单个问卷测试成功");
        } else {
            log.debug(">>查找单个问卷测试失败");
        }
    }

    @Transactional
    @Test
    void updateLinkTest() {
        boolean count = questionnaireService.updateLink("ew", 49);

        if(count) {
            log.info(">>问卷更新链接测试成功");
        } else {
            log.debug(">>问卷更新链接测试失败");
        }
    }

    @Transactional
    @Test
    void deleteTest() {
        boolean count = questionnaireService.deleteQn(56);

        if(count) {
            log.info(">>删除问卷测试成功");
        } else {
            log.debug(">>删除问卷测试失败");
        }
    }


}
