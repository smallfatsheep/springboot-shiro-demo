package com.example.springbootshirodemo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class JwtUtils {
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    public static boolean verify(String token, String username, String password){
        try{
            //获取加密算法对象(密钥)
            Algorithm algorithm = Algorithm.HMAC256(password);
            //获取JWT 验证对象
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static String sign(String username, String password){
        try{
            Date data = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create()
                    .withClaim("username",username)
                    .withExpiresAt(data)
                    .sign(algorithm);
        }catch (Exception e){
            return null;
        }
    }
    public static String getUsername(String token){
        if (token == null || "".equals(token)){
            return null;
        }
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTCreationException e){
            return null;
        }
    }
}
