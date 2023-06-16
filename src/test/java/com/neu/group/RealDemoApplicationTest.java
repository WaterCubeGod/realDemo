package com.neu.group;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RealDemoApplicationTest {
    @Test
    void test() {
        String[] ints = new String[1];
        ints[0] ="1";
        RealDemoApplication.main(ints);
        int a = 1;
        int e = 1;
        assertEquals(e,a);
    }
}
