package com.neu.group;

import com.neu.group.domain.Option;
import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionTest {
    Logger log = Logger.getLogger(QuestionTest.class);
    @Autowired
    QuestionService questionService;

    @Transactional
    @Test
    void insertTest() {
        List<String> contents = new ArrayList<>();
        contents.add("k");
        contents.add("f");
        contents.add("c");

//        List<String> columns = new ArrayList<>();
//        columns.add("4");
//        columns.add("5");
//        columns.add("6");

        List<Integer> scores = new ArrayList<>();
        scores.add(0);
        scores.add(50);
        scores.add(100);

        List<Option> options = new ArrayList<>();
        Option option = new Option(2,3,"量表",0,5,contents,null,scores);
        options.add(option);

        boolean flag = questionService.addQuestion(options);

        if(flag) {
            log.info(">>添加题目测试成功");
        } else {
            log.debug(">>添加问卷测试失败");
        }
    }

    @Transactional
    @Test
    void searchTest() {
        List<Option> options = questionService.selectAllByQnId(2);
        System.out.println(options);

        if(options != null) {
            log.info(">>查找题目测试成功");
        } else {
            log.debug(">>查找问卷测试失败");
        }
    }

    @Transactional
    @Test
    void searchByLinkTest() {
        List<Option> options = questionService.selectByLink(
                "http://localhost:8080/pages/answerSheet/index.html?link=d4c13f98-6862-43c5-982d-18b12d1df7cc"
        );
        System.out.println(options);

        if(options != null) {
            log.info(">>通过链接查找题目测试成功");
        } else {
            log.debug(">>通过链接查找题目测试失败");
        }
    }

    @Transactional
    @Test
    void searchQuestionnaireTest() {
        Questionnaire questionnaire = questionService.selectQuestionnaire(
                "http://localhost:8080/pages/answerSheet/index.html?link=d4c13f98-6862-43c5-982d-18b12d1df7cc"
        );
        System.out.println(questionnaire);

        if(questionnaire != null) {
            log.info(">>通过链接查找问卷测试成功");
        } else {
            log.debug(">>通过链接查找问卷测试失败");
        }
    }
}
