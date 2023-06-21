package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionnaireService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
