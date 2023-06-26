package com.neu.group.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.controller.utils.R;
import com.neu.group.dao.AnswerDao;
import com.neu.group.domain.Answer;
import com.neu.group.domain.Option;
import com.neu.group.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping
public class AnswerController {
    @Autowired
    AnswerService answerService;
    public R createUserAnswer(@RequestBody JSONArray jsonArray){
        List<Answer> answers = JSONObject.parseArray(jsonArray.toJSONString(), Answer.class);
        boolean flag = answerService.addAnswer(answers);
        return new R(flag,"",flag ? "成功":"失败");
    }
}
