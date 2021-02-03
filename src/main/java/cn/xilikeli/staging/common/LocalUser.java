package cn.xilikeli.staging.common;

import cn.xilikeli.staging.model.AccountDO;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 基于 ThreadLocal 的线程安全的当前登录用户, 如果用户未登录, 则得到 null
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/1/14
 * @since JDK1.8
 */
public class LocalUser {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 得到当前登录用户信息
     *
     * @return 返回当前登录用户信息, 如果未登录则返回 null
     */
    public static AccountDO getLocalUser() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        return (AccountDO) map.get("account");
    }

    /**
     * 获取当前登录用户的用户级别
     *
     * @return 返回当前登录用户的用户级别, 如果未登录则返回 null
     */
    public static Integer getScope() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        return (Integer) map.get("scope");
    }

    /**
     * 设置当前登录用户信息
     *
     * @param account 当前登录用户信息
     * @param scope   当前登录用户的用户级别
     */
    public static void setLocalUser(AccountDO account, Integer scope) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("account", account);
        map.put("scope", scope);
        LocalUser.threadLocal.set(map);
    }

    /**
     * 清理当前登录用户信息
     */
    public static void clearLocalUser() {
        LocalUser.threadLocal.remove();
    }

}