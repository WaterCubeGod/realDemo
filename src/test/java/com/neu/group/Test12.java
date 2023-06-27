package com.neu.group;

import com.neu.group.dao.AnswerDao;
import com.neu.group.dao.QuestionDao;
import com.neu.group.domain.Answer;
import com.neu.group.domain.Question;
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

    @Autowired
    QuestionDao questionDao;
    @Test
    @Transactional
    void testDemo(){
//        List<Answer> answers = answerDao.selectAnswer2(90);
//        for (Answer answer:
//             answers) {
//            System.out.println(answer);
//        }
//        List<SingleAnswer> singleAnswers = answerDao.selectAnswerOptionById(1, 21);
//        for (SingleAnswer singleAnswer:
//             singleAnswers) {
//            System.out.println(singleAnswer);
//        }
        Question question = questionDao.selectQuestion(90, 4);
        System.out.println(question);
    }
}
