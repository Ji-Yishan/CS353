package com.parttime.cs353.utils;

import org.springframework.util.DigestUtils;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-27 22:32
 **/
public class PasswordEncoder {
    /**
     * 密码加密
     * @param rawPassword 登录时传入的密码
     */
    public static String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    /**
     * 密码对比
     * @param rawPassword 登录时传入的密码
     * @param encodedPassword 数据库保存的加密过的密码
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
    }

}
