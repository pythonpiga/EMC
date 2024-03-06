package com.broad.emc.common.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


public class TokenUtil {
    private static final long EXPIRE_TIME = 5 * 60 * 60 * 1000;   //五小时 5 * 60 * 60 * 1000
    private static final String TOKEN_SECRET = "broad";  //密钥盐

    /**
     * 签名生成
     * 
     * @param account
     * @return
     */
    public static String sign(String account) {
        String token = null;
        
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("Username", account)
//                    .withAudience(staff.getUsername())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
//            System.err.println("认证通过：");
//            System.err.println("Username: " + jwt.getClaim("Username").asString());
//            System.err.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
