package com.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName JwtConfig
 * @Author 王孟伟
 * @Date 2021/10/29 16:27
 * @Version 1.0
 */
@Component
public class JwtConfig {

    /**
     * @ConstructorProperties
     */
    @Value("${config.jwt.secret}")
    private String secret;

    @Value("${config.jwt.expire}")
    private long  expire;

    @Value("${config.jwt.header}")
    private String header;

    /**
     * 生成Token
     * @param subject
     * @return
     */
    private String createToken (String subject){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+expire*1000);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

}
