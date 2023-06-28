package com.neu.group;

import com.neu.group.domain.*;
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
        SingleOption singleOption1 = new SingleOption("31","431",1);
        SingleOption singleOption2 = new SingleOption(1,1,"23",1,1,"31","431",1);
        String a = singleOption2.toString();
        singleOption2.setColumn("q3");
        List<String> A = new ArrayList<>();
        List<String> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        A.add("1");
        B.add("2");
        C.add(1);
        Option option1 = new Option(A,B,C);
        option1.setScore(C);

        SingleAnswer singleAnswer1 = new SingleAnswer(1,1);
        SingleAnswer singleAnswer2 = new SingleAnswer(1,1,1);
        SingleAnswer singleAnswer3 = new SingleAnswer(1,"1");
        SingleAnswer singleAnswer4 = new SingleAnswer(1,"1",1,1,1);
        singleAnswer4.toString();
        singleAnswer4.getId();

        Answer answer1 = new Answer(1,1,"1",1,1,1,"1","1","1",C,C,1);
        Answer answer2 = new Answer(1,1,"1",1,1,1,"1","1","1",C,C,1,1);
        answer2.setaId(1);
        answer2.toString();


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
        Option option = new Option(2,7,"量表",0,5,contents,null,scores);
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
