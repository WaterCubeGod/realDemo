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

    @PutMapping("/addQuestion")
    public R createQuestion(@RequestBody JSONArray jsonArray) {
        List<Option> options = JSONObject.parseArray(jsonArray.toJSONString(),Option.class);
        boolean flag = questionService.addQuestion(options);

        return new R(flag, "", flag ? "成功" : "失败");
    }

    @PostMapping("/seeQuestion")
    public R seeQuestion(@RequestBody int qnId) {
        List<Option> options = questionService.selectAllByQnId(qnId);

        return new R(true, options, "");
    }

    @GetMapping("/seeQuestion/{link}")
    public R requestQuestion(@PathVariable String link) {

        List<Option> options = questionService.selectByLink("http://localhost:8080/pages/answerSheet/index.html?link=" + link);

        return new R(true, options, "");
    }

}
