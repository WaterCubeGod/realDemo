package com.neu.group.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.controller.utils.R;
import com.neu.group.domain.Option;
import com.neu.group.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @PutMapping("/addquestion")
    public R createQuestion(@RequestBody JSONArray jsonArray) {
        List<Option> options = JSONObject.parseArray(jsonArray.toJSONString(),Option.class);;
        boolean flag = questionService.addQuestion(options);

        return new R(flag, "", flag ? "成功" : "失败");
    }

}
