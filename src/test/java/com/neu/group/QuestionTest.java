package com.neu.group;

import com.neu.group.domain.Option;
import com.neu.group.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionTest {

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

        questionService.addQuestion(options);
    }

    @Transactional
    @Test
    void searchTest() {
        List<Option> options = questionService.selectAllByQnId(2);
        System.out.println(options);
    }
}
