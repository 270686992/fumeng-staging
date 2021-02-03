package cn.xilikeli.staging.common.util;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.algorithms.Type;
import com.amdelamar.jhash.exception.InvalidHashException;

/**
 * <p>
 * 加密工具类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public class EncryptUtil {

    /**
     * 设置密文密码
     *
     * @param password 原始密码
     * @return 返回加密后的密码
     */
    public static String encrypt(String password) {
        char[] chars = password.toCharArray();
        return Hash.password(chars).algorithm(Type.PBKDF2_SHA256).create();
    }

    /**
     * 验证加密密码
     *
     * @param encryptedPassword 密文密码
     * @param plainPassword     明文密码
     * @return 验证成功返回 true,否则返回 false
     */
    public static boolean verify(String encryptedPassword, String plainPassword) {
        char[] chars = plainPassword.toCharArray();
        try {
            return Hash.password(chars).algorithm(Type.PBKDF2_SHA256).verify(encryptedPassword);
        } catch (InvalidHashException e) {
            return false;
        }
    }

}