package com.neu.group;


import com.neu.group.domain.Project;
import com.neu.group.service.ProjectService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProjectTest {

    @Autowired
    private ProjectService projectService;

    Logger log = Logger.getLogger(ProjectTest.class);

    @Test
    void searchProject(){
        List<Project> projects = projectService.searchProjectByName(37,"");
        List<Project> projects1 = projectService.selectProjectByUser(37);

        log.info(projects);
        log.info(projects1);

    }

    @Test
    void addProject(){
        boolean flag = projectService.addProject("测试111",
                "test测试用例", 37);

        log.info(flag);
    }

    @Test
    void updateProject(){
        boolean flag = projectService.updateProject("测试更新",
                "test测试用例", 3);

        log.info(flag);
    }

    @Test
    void deleteProject(){
        boolean flag = projectService.deleteProject(3);

        log.info(flag);
    }

}
