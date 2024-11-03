package com.parttime.cs353.test;


import com.parttime.cs353.utils.RsaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 23:18
 **/

@SpringBootTest
public class RsaUtilsTest {
    private String publicFile = "C:\\auth_key\\rsa_key.pub";
    private String privateFile = "C:\\auth_key\\rsa_key";
    private String secret = "IsabellaSecret";

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicFile, privateFile, secret, 2048);
    }
}
