package com.neu.group.controller;

import com.neu.group.controller.utils.R;
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
        String projectBelong = jsonObject.getString("projectBelong");
        String type = jsonObject.getString("type");
        String createTime = jsonObject.getString("createTime");
        String finishTime = jsonObject.getString("finishTime");
        return new R(true,questionnaireService.
                addQuestionnaire(name,description,projectBelong,type,createTime,finishTime),
                "");
    }
}
