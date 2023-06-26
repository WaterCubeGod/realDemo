package com.neu.group;

import com.neu.group.dao.AnswerDao;
import com.neu.group.domain.Answer;
import com.neu.group.domain.SingleAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class Test12 {
    @Autowired
    AnswerDao answerDao;
    @Test
    @Transactional
    void testDemo(){
        List<SingleAnswer> singleAnswers = answerDao.selectAnswerOptionById(1, 21);
        for (SingleAnswer singleAnswer:
             singleAnswers) {
            System.out.println(singleAnswer);
        }
    }
}
