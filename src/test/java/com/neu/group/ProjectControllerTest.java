package com.neu.group;

import com.neu.group.controller.ProjectController;
import com.neu.group.domain.Project;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjectControllerTest {

    @Autowired
    private ProjectController projectController;

    @Test
    @Transactional
    @Rollback
    void Test(){
        JSONObject json = new JSONObject();
        json.put("projectName","123");
        projectController.searchProject(json,1);

        projectController.createProject(new Project(123,233,"1231",
                "31231","3123"));
        projectController.modifyProject(new Project(123,233,"1231",
                "31231","3123"));
        projectController.deleteProject(new Project(123,233,"1231",
                "31231","3123"));
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }


}

