package com.neu.group;

import com.neu.group.controller.QuestionnaireController;
import com.neu.group.controller.utils.R;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class QuestionnaireControllerTest {

    Logger log = Logger.getLogger(QuestionnaireControllerTest.class);
    @Autowired
    QuestionnaireController questionnaireController;

    @Transactional
    @Test
    void createQuestionnaire(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","1");
        jsonObject.put("description","1");
        jsonObject.put("projectBelong","1");
        jsonObject.put("type","1");
        jsonObject.put("createTime","2023-06-02 12:49:17");
        jsonObject.put("finishTime","2023-06-12 12:49:17");

        R r = questionnaireController.createQuestionnaire(jsonObject);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void selectAll(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","2");

        R r = questionnaireController.selectAll(jsonObject);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void submitQuestionnaire(){

        R r = questionnaireController.submitQuestionnaire(68);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void deleteQuestionnaire(){

        R r = questionnaireController.deleteQuestionnaire(68);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }

    @Transactional
    @Test
    void selectById(){

        R r = questionnaireController.selectById(68);

        if(r != null){
            log.info(">>测试成功");
        } else {
            log.debug(">>测试失败");
        }
    }
}
