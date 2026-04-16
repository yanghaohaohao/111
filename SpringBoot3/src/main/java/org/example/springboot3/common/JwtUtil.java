package org.example.springboot3.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


public class JwtUtil {
    private static final String KEY="MySecretKeyForJWTTokenGeneration12345";
    private static final int EXPIRE_TIME = 1000*60*60*24;

    //一、把KEY转成创建密钥的格式
    private static SecretKey getSecretKey(){
        byte[] keyBytes = KEY.getBytes();
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
    };

    //二、创建token,并把用户id和身份放进去
    public static String generateToken(Integer userId,String role){
        return Jwts.builder()
                .claim("userId",userId)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    //三、校验密钥
    private static Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    //四、获取密钥中的用户id

    public static Integer getUserId(String token){
        return parseToken(token).get("userId", Integer.class);
    }

    //五、获取密钥中的用户身份

    public static String getRole(String token){
        return parseToken(token).get("role", String.class);
    }

}
