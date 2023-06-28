package com.neu.group.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.controller.utils.*;
import com.neu.group.domain.Option;
import com.neu.group.domain.Questionnaire;
import com.neu.group.service.QuestionService;

import com.neu.group.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.neu.group.controller.utils.ParesToJsonArray.parseToJsonArray;
@Controller
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionnaireService questionnaireService;

    @PutMapping("/addQuestion")
    public R createQuestion(@RequestBody JSONArray jsonArray) {
        List<Option> options = JSONObject.parseArray(jsonArray.toJSONString(),Option.class);
        boolean flag = questionService.addQuestion(options);

        return new R(flag, "", flag ? "成功" : "失败");
    }

    @PostMapping("/seeQuestion")
    public R seeQuestion(@RequestBody JSONObject jsonObject) {
        List<Option> options = questionService.selectAllByQnId(jsonObject.getInteger("qnId"));
        Questionnaire questionnaire = questionnaireService.selectById(jsonObject.getInteger("qnId"));

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(parseToJsonArray(options));
        jsonArray.add(JSONObject.toJSONString(questionnaire));

        return new R(true, jsonArray, "");
    }

    @GetMapping("/seeQuestion/{link}")
    public R requestQuestion(@PathVariable String link) {
        String qLink = "http://localhost:8080/pages/answerSheet/index.html?link=" + link;
        List<Option> options = questionService.selectByLink(qLink);
        Questionnaire questionnaire = questionService.selectQuestionnaire(qLink);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(parseToJsonArray(options));
        jsonArray.add(JSONObject.toJSONString(questionnaire));

        return new R(true, jsonArray, "");
    }


}
