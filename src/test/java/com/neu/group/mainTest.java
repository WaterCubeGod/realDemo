package com.neu.group;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class mainTest {
    @Test
    void test() {
        String[] ints = new String[1];
        ints[0] ="1";
        RealDemoApplication.main(ints);
    }
}
