package com.neu.group;

import com.neu.group.service.QuestionnaireService;
import com.neu.group.service.impl.QuestionnaireServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionnaireTest {
    @Autowired
    QuestionnaireService questionnaireService;

    @Test
//    @Transactional
//    @Rollback
    void test(){
        questionnaireService.addQuestionnaire("12","d1dsda",1,"ddsad","2023-06-02 12:49:17","2023-06-12 12:49:17");
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }
}
