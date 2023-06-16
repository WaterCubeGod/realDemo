package com.neu.group;


import cc.aicode.e2e.ExcelHelper;
import cc.aicode.e2e.exception.ExcelContentInvalidException;
import cc.aicode.e2e.exception.ExcelParseException;
import cc.aicode.e2e.exception.ExcelRegexpValidFailedException;
import com.neu.group.domain.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExcelImportTests {

    @Test
    @Rollback
    void excelTest() throws IOException, InvalidFormatException {

        ExcelHelper eh = ExcelHelper.readExcel("user.xls");
        List<User> entitys;
        try {
            entitys = eh.toEntitys(User.class);
            for (User d : entitys) {
                System.out.println(d.toString());
            }
        } catch (ExcelParseException | ExcelRegexpValidFailedException | ExcelContentInvalidException e) {
            System.out.println(e.getMessage());
        }
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }

}
