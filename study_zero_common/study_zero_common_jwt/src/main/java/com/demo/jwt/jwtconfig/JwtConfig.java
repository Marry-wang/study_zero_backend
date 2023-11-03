package com.demo.jwt.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName JwtConfig
 * @Author 王孟伟
 * @Date 2021/10/29 16:27
 * @Version 1.0
 */

/**
 * https://blog.csdn.net/AkiraNicky/article/details/99307713?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163549409116780265479171%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=163549409116780265479171&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-99307713.first_rank_v2_pc_rank_v29&utm_term=springBoot%E6%95%B4%E5%90%88jwt&spm=1018.2226.3001.4187
 */
@Component
@ConfigurationProperties(prefix = "config.jwt")
@Data
public class JwtConfig {

    /**
     * @ConstructorProperties
     */
//    @Value("${config.jwt.secret}")
    private String secret;

    //    @Value("${config.jwt.expire}")
    private long expire;

    //    @Value("${config.jwt.header}")
    private String header;

    /**
     * 生成Token
     *
     * @param subject
     * @return
     */
    public String createToken(String subject) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 获取token中注册信息
     *
     * @param token
     * @return
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token是否过期失效
     *
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     *
     * @param token
     * @return
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

}
