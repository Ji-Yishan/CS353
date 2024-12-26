package com.parttime.cs353.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-27 23:59
 **/
@SpringBootTest
public class keyGenerater {
    @Test
    public void test(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[64]; // secureRandom.nextBytes(key);
        //
        String encodedKey = Base64.getEncoder().encodeToString(key);

    }
}
