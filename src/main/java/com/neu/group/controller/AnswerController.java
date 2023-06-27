package com.neu.group.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.controller.utils.R;
import com.neu.group.dao.AnswerDao;
import com.neu.group.domain.Answer;
import com.neu.group.domain.Option;
import com.neu.group.service.AnswerService;
import com.neu.group.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.neu.group.controller.utils.ParesToJsonArray.parseToJsonArray;

@Controller
@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;
    @PostMapping("/add")
    public R createUserAnswer(@RequestBody JSONArray jsonArray){
        List<Answer> answers = JSONObject.parseArray(jsonArray.toJSONString(), Answer.class);
        boolean flag = answerService.addAnswer(answers);
        return new R(flag,"",flag ? "成功":"失败");
    }
    @PostMapping("/count")
    public R getAnsweredQuestionnaireInProject(@RequestBody JSONObject jsonObject){
        Integer projectId = jsonObject.getInteger("projectId");
        List<Answer> answers = answerService.selectAllQuestionnaireInProject(projectId);
        boolean flag = !answers.isEmpty();
        return new R(flag, answers,flag?"成功":"失败");
    }
    @PostMapping("/statistics")
    public R getStatisticsDate(@RequestParam("qnId") int qnId){
        List<Option> options = questionService.selectAllByQnId(qnId);
        List<Answer> answers = answerService.selectAllAnswer(qnId);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(parseToJsonArray(options));
        jsonArray.add(parseToJsonArray(answers));
        return new R(true,jsonArray,"");
    }
    @PostMapping("/showDetail")
    public R getAnswer(@RequestParam("qnId") int qnId,@RequestParam("aId") int aId){
        List<Answer> answers = answerService.selectAllByQnId(qnId, aId);
        return new R(true,answers,"");
    }

    @PostMapping("/getAnswerInSameQuestion")
    public R getAnswerInSameQuestion(@RequestParam("qnId") int qnId,@RequestParam("qId") int qId){
        List<Option> options = questionService.selectSameQuestionInProject(qId, qnId);
        List<Answer> answers = answerService.selectAnswerInSameQuestion(options, qnId);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(parseToJsonArray(options));
        jsonArray.add(parseToJsonArray(answers));
        return new R(true,jsonArray,"");
    }

}
