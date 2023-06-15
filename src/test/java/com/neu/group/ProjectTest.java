package com.neu.group;


import com.neu.group.domain.Project;
import com.neu.group.service.ProjectService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ProjectTest {

    @Autowired
    private ProjectService projectService;

    Logger log = Logger.getLogger(ProjectTest.class);

    @Test
    @Transactional
    @Rollback
    void searchProject(){
        List<Project> projects = projectService.searchProjectByName(37,"");
        List<Project> projects1 = projectService.selectProjectByUser(37);

        log.info(projects);
        log.info(projects1);

    }

    @Test
    @Transactional
    @Rollback
    void addProject(){
        boolean flag = projectService.addProject("测试11111",
                "test测试用例", 37);

        log.info(flag);
    }

    @Test
    @Transactional
    @Rollback
    void updateProject(){
        boolean flag = projectService.updateProject("测试更新",
                "test测试用例", 11);

        log.info(flag);
    }

    @Test
    @Transactional
    @Rollback
    void deleteProject(){
        boolean flag = projectService.deleteProject(11);

        log.info(flag);
    }

    /**
     *
     */
    @Test
    void Test(){
        Project project = new Project(1,1,"1","1","1");
        project.getProjectDescription();
        project.getProjectId();
        project.getCreateTime();
        project.getProjectName();
        project.getUserId();
    }

}
