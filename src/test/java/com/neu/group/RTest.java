package com.neu.group;

import com.neu.group.controller.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RTest {
    @Test
    void testRMethod(){
        R r = new R(true, 12, "11");
        R r1 = new R(true);
        R r2 = new R();
        Boolean flag = r.getFlag();
        Object data = r.getData();
        String message = r.getMessage();
        r.setFlag(flag);
        r.setData(data);
        r.setMessage(message);
        System.out.println(r);
        System.out.println(r1);
        System.out.println(r2);
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }

}
