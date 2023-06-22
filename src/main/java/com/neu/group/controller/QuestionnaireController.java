package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionnaireService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    QuestionnaireService questionnaireService;
    @PutMapping(value = "/createQuestionnaire",headers = "Accept=application/json")
    public R createQuestionnaire(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int projectBelong = jsonObject.getInt("projectBelong");
        String type = jsonObject.getString("type");
        String createTime = jsonObject.getString("createTime");
        String finishTime = jsonObject.getString("finishTime");
        boolean flag = questionnaireService.
                addQuestionnaire(name,description,projectBelong,type,createTime,finishTime);

        Questionnaire questionnaire = questionnaireService.selectQn(projectBelong, name);
        return new R(flag, questionnaire, "");
    }

    @PostMapping("/selectAll")
    public R selectAll(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        int projectBelong = jsonObject.getInt("id");
        return new R(true, questionnaireService.selectAll(projectBelong), "");
    }

    @GetMapping("/submit")
    public R submitQuestionnaire(@RequestBody int id) {
        // 生成唯一的问卷链接
        String uniqueId = UUID.randomUUID().toString();
        String questionnaireLink = "http://localhost:8080/pages/answerSheet/index.js?link=" + uniqueId;

        // 将问卷链接与用户的填写数据关联存储，可以使用数据库或其他存储解决方案
        boolean flag = questionnaireService.updateLink(questionnaireLink, id);

        // 返回问卷链接给用户
        return new R(flag, questionnaireLink, "");
    }

}
