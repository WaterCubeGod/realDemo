package com.neu.group;

import com.neu.group.service.QuestionnaireService;
import com.neu.group.service.impl.QuestionnaireServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class QuestionnaireTest {
    @Autowired
    QuestionnaireService questionnaireService;

    @Test
//    @Transactional
//    @Rollback
    void test(){
        questionnaireService.addQuestionnaire("12","d1dsda","sdasd","ddsad","2023-06-02 12:49:17","2023-06-12 12:49:17");
    }
}
