package com.jiangjf.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.util.Calendar;
import java.util.Date;

/**
 * @author jiangjf
 * @date 2022/1/28
 */
public class JwtUtil {
    /**
     * 密钥，仅服务端存储
     */
    private static final String SECRET = "ko346134h_we]rg3in_yip1!";

    /**
     * @param subject   消息主体
     * @param issueDate 签发时间
     * @return
     */
    public static String createToken(String subject, Date issueDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(issueDate);
        c.add(Calendar.DAY_OF_MONTH, 20);

        String compactJws = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                .setExpiration(c.getTime())
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                .compact();
        return compactJws;
    }

    /**
     * 解密 jwt
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static String parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            if (claims != null) {
                return claims.getSubject();
            }
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("jwt过期了");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("出现了其他异常");
        }
        return "";
    }
}
