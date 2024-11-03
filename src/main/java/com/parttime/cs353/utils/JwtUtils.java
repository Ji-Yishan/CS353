package com.parttime.cs353.utils;


import com.parttime.cs353.config.jwt.Payload;
import com.parttime.cs353.config.jwt.SecurityUser;
import com.parttime.cs353.pojo.data.AdminDO;
import com.parttime.cs353.pojo.data.CompanyDO;
import com.parttime.cs353.pojo.data.UserDO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:生成token以及校验token相关方法
 * @author: Isabella
 * @create: 2024-10-26 14:59
 **/
@Slf4j
public class JwtUtils {
    private static final String JWT_PAYLOAD_USER_KEY = "user";
    private static final String JWT_PAYLOAD_ADMIN_KEY = "admin";
    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

// 暂时用这个，想换的时候再到test里面的key generater那边生成新的
//    private final static String SECRET_KEY =  Base64.getEncoder().encodeToString("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==".getBytes(StandardCharsets.UTF_8));


    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 加密token
     *
     * @param userInfo   载荷中的数据
     * @param
     * @param expire     过期时间，单位分钟
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(UserDO userInfo,PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .setSubject(userInfo.getPhone())
                .claim("userInfo", JsonUtils.toString(userInfo))
                .claim("type",userInfo.getType())
                .claim("userId",userInfo.getUid())
                .claim("status",userInfo.getStatus())
//                .setId(createJTI())
                .setIssuedAt(new Date())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
//                去掉RSA的private key
                .signWith(privateKey, SignatureAlgorithm.RS256)
//                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

    public static String generateCompanyTokenExpireInMinutes(CompanyDO userInfo, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .setSubject(userInfo.getPhone())
                .claim("userInfo", JsonUtils.toString(userInfo))
                .claim("type","company")
                .claim("userId",userInfo.getCid())
                .claim("status",userInfo.getStatus())
//                .setId(createJTI())
                .setIssuedAt(new Date())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
//                去掉RSA的private key
                .signWith(privateKey, SignatureAlgorithm.RS256)
//                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

    public static String generateAdminTokenExpireInMinutes(AdminDO userInfo, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .setSubject(userInfo.getPhone())
                .claim("userInfo", JsonUtils.toString(userInfo))
                .claim("type","admin")
                .claim("userId",userInfo.getAdminId())
//                .claim("status",userInfo.getStatus())
//                .setId(createJTI())
                .setIssuedAt(new Date())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
//                去掉RSA的private key
                .signWith(privateKey, SignatureAlgorithm.RS256)
//                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

    /**
     * 私钥加密token（秒钟类）
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位秒
     * @return JWT
     */
public static String generateTokenExpireInSeconds(UserDO userInfo, PrivateKey privateKey, int expire) {
    return Jwts.builder()
            .setSubject(userInfo.getPhone())
//                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
            .claim("type",userInfo.getType())
            .claim("id",userInfo.getUid())
            .setId(userInfo.getUid()+"")
//                .setId(createJTI())
            .setIssuedAt(new Date())
            .setExpiration(DateTime.now().plusSeconds(expire).toDate())
//                去掉RSA的private key
            .signWith(privateKey, SignatureAlgorithm.RS256)
//                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
            .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }
    public static <T> Payload<T> getAdminInfoFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_ADMIN_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }
    /**
     * 获取token中的载荷信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        Object id=body.get("userId");
        id=Integer.parseInt(id+"");
        claims.setId( id+"");
//        这个好像注入不进去，但是没关系我反正也不用
        Object h= body.get("userInfo");
        SecurityUser securityUser=new SecurityUser((Integer) body.get("status"));
        claims.setUserInfo((T) securityUser);
        claims.setExpiration(body.getExpiration());

        return claims;
    }

    public static Claims getTokenBody(String token, PublicKey publicKey){
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return body;
    }



}