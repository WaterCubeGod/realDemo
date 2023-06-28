package com.neu.group;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.controller.AnswerController;
import com.neu.group.controller.utils.R;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AnswerControllerTest {
    Logger log = Logger.getLogger(AnswerControllerTest.class);
    @Autowired
    AnswerController answerController;

    @Transactional
    @Test
    void selectAll(){
        JSONObject jsonObject = new JSONObject();
        String[] list = {"aewf","awife"};
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("qnId",2);
        jsonObject.put("qId",4);
        jsonObject.put("title","2");
        jsonObject.put("req",0);
        jsonObject.put("type",3);
        jsonObject.put("content",list);
        jsonArray.add(jsonObject);

        R r = answerController.createUserAnswer(jsonArray);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void getAnsweredQuestionnaireInProject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectId",2);


        R r = answerController.getAnsweredQuestionnaireInProject(jsonObject);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void getStatisticsDate(){

        R r = answerController.getStatisticsDate(90);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void getAnswer(){

        R r = answerController.getAnswer(90,1);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void getAnswerInSameQuestion(){

        R r = answerController.getAnswerInSameQuestion(90,1);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }
}
