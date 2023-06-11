package com.neu.group.controller;

import com.neu.group.controller.utils.R;
import com.neu.group.domain.Project;
import com.neu.group.service.ProjectService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/{userId}")
    public R searchProject(@RequestBody JSONObject jsonObject, @PathVariable int userId){
        String projectName = jsonObject.getString("projectName");

        return new R(true, projectService.searchProjectByName(userId,projectName), "");
    }

    @PutMapping
    public R createProject(@RequestBody Project project) {
        boolean flag = projectService.addProject(project.getProjectName(),
                project.getProjectDescription(), project.getUserId());

        return new R(flag, "", flag ? "创建成功" : "创建失败,可能存在相同的工程名");
    }

    @PostMapping
    public R modifyProject(@RequestBody Project project) {
        boolean flag = projectService.updateProject(project.getProjectName(),
                project.getProjectDescription(), project.getProjectId());
        return new R(flag, "", flag ? "修改成功" : "修改失败,可能存在相同的工程名");
    }

    @DeleteMapping
    public R deleteProject(@RequestBody Project project) {
        boolean flag = projectService.deleteProject(project.getProjectId());
        return new R(flag, "", flag ? "删除成功" : "删除失败");
    }


}
