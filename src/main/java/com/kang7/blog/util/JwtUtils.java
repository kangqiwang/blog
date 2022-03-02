package com.kang7.blog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "gyg.jwt")
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;

    public String generateToken(long userId){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")    //设置头部信息
                .setSubject(userId + "")    //设置用户id
                .setIssuedAt(nowDate)       //设置开始时间
                .setExpiration(expireDate)  //设置过期时间
                .signWith(SignatureAlgorithm.HS512, secret)  //设置加密算法
                .compact();

    }

    public Claims getClaimByToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.debug("problems when get token" + e);
            return null;
        }
    }

    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

}