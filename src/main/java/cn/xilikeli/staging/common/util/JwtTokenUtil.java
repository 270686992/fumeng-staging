package cn.xilikeli.staging.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>
 * JWT Token 令牌工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 16:15
 * @since JDK1.8
 */
@Component
public class JwtTokenUtil {
    /**
     * 用于生成 JWT 令牌的 key
     */
    private static String jwtKey;

    /**
     * JWT 令牌过期时间,单位: 秒
     */
    private static Integer expireTime;

    /**
     * 用户默认分组级别
     */
    private static final Integer DEFAULT_SCOPE = 8;

    @Value("${staging.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtTokenUtil.jwtKey = jwtKey;
    }

    @Value("${staging.security.token-expire-time}")
    public void setExpireTime(Integer expireTime) {
        JwtTokenUtil.expireTime = expireTime;
    }

    /**
     * 使用 uid 和 scope 生成 JWT 令牌并返回
     *
     * @param uid   获取 JWT 令牌的用户的标识
     * @param scope 获取 JWT 令牌的用户的分组级别
     * @return 返回生成的 JWT 令牌
     */
    public static String makeToken(Integer uid, Integer scope) {
        return JwtTokenUtil.getToken(uid, scope);
    }

    /**
     * 使用 uid 和用户默认分组级别生成 JWT 令牌并返回
     *
     * @param uid 获取 JWT 令牌的用户的标识
     * @return 返回生成的 JWT 令牌
     */
    public static String makeToken(Integer uid) {
        return JwtTokenUtil.getToken(uid, JwtTokenUtil.DEFAULT_SCOPE);
    }

    /**
     * 校验 JWT 令牌并获取令牌中的信息
     *
     * @param token 校验的 JWT 令牌
     * @return 校验成功返回令牌中存储的信息, 校验失败返回 Optional.empty()
     */
    public static Optional<Map<String, Claim>> verifyTokenAndGetClaims(String token) {
        // 校验令牌的算法
        Algorithm algorithm = Algorithm.HMAC256(JwtTokenUtil.jwtKey);
        // 校验令牌的对象
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJwt;
        try {
            // 校验令牌
            decodedJwt = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            // 校验失败,返回 Optional.empty()
            return Optional.empty();
        }
        // 校验成功,返回令牌中存储的信息
        return Optional.of(decodedJwt.getClaims());
    }

    /**
     * 校验 JWT 令牌是否合法
     *
     * @param token 校验的 JWT 令牌
     * @return 校验成功返回 true,校验失败返回 false
     */
    public static Boolean verifyToken(String token) {
        try {
            // 校验令牌的算法
            Algorithm algorithm = Algorithm.HMAC256(JwtTokenUtil.jwtKey);
            // 校验令牌的对象
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 校验令牌
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            // 校验失败
            return false;
        }
        // 校验成功
        return true;
    }

    /**
     * 使用 uid 和 scope 生成 JWT 令牌
     *
     * @param uid   获取 JWT 令牌的用户的标识
     * @param scope 获取 JWT 令牌的用户的分组级别
     * @return 返回生成的 JWT 令牌
     */
    private static String getToken(Integer uid, Integer scope) {
        // jwt 的常用的两个库: jjwt,auth0,这里使用 auth0
        // 生成令牌的算法
        Algorithm algorithm = Algorithm.HMAC256(JwtTokenUtil.jwtKey);

        // 用于获取令牌签发时间和过期时间
        Map<String, Date> map = JwtTokenUtil.calculateExpiredIssues();

        // 生成 JWT 令牌
        return JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                .withIssuedAt(map.get("now"))
                .withExpiresAt(map.get("expiredTime"))
                .sign(algorithm);
    }

    /**
     * 计算 JWT 令牌过期时间
     *
     * @return 返回计算出的 JWT 令牌过期时间
     */
    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>(16);
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 记录签发时间
        Date now = calendar.getTime();
        // 计算过期时间
        calendar.add(Calendar.SECOND, JwtTokenUtil.expireTime);
        // 传入签发时间和过期时间返回
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }
}