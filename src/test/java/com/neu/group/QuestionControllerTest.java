package com.neu.group;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.controller.QuestionController;
import com.neu.group.controller.utils.R;
import com.neu.group.domain.Option;
import com.neu.group.service.QuestionService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class QuestionControllerTest {

    Logger log = Logger.getLogger(QuestionControllerTest.class);
    @Autowired
    QuestionController questionController;

    @Transactional
    @Test
    void createQuestionnaire(){
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

        R r = questionController.createQuestion(jsonArray);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void seeQuestion(){
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("qnId",2);

        R r = questionController.seeQuestion(jsonObject);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void requestQuestion(){

        R r = questionController.requestQuestion("http://localhost:8080/pages/answerSheet/index.html?link=d4c13f98-6862-43c5-982d-18b12d1df7cc");

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }


}
