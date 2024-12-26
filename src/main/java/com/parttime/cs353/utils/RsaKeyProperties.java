package com.parttime.cs353.utils;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 16:11
 **/
@Data
@ConfigurationProperties(prefix = "rsa.key", ignoreInvalidFields = true)
public class RsaKeyProperties {
    private String publicKeyPath;
    private String privateKeyPath;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    /**
     * use to initialize private and public key
     */
    @PostConstruct
    public void loadRsaKey() throws Exception {
        if (publicKeyPath != null) {
            publicKey = RsaUtils.getPublicKey(publicKeyPath);
        }
        if (privateKeyPath != null) {
            privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        }
    }
}