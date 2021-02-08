package cn.xilikeli.staging.common.enumeration;

import java.util.stream.Stream;

/**
 * <p>
 * 登录类型枚举类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/6
 * @since JDK1.8
 */
public enum LoginTypeEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 用户名、密码登录
     */
    USERNAME_PASSWORD_IDENTITY(0, "用户名、密码登录"),

    /**
     * 微信登录
     */
    WX_IDENTITY(1, "微信登录"),

    /**
     * 邮箱登录
     */
    EMAIL_IDENTITY(2, "邮箱登录"),

    /**
     * 手机号登录
     */
    MOBILE_IDENTITY(3, "手机号登录");

    /**
     * 枚举值
     */
    private final Integer value;

    /**
     * 枚举描述
     */
    private final String description;

    /**
     * 构造函数
     *
     * @param value       枚举值
     * @param description 枚举描述
     */
    LoginTypeEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * 将枚举值转换为对应的枚举类型
     *
     * @param value 枚举值
     * @return 转换成功返回枚举值对应的枚举类型, 转换失败返回未定义枚举
     */
    public static LoginTypeEnum toEnum(Integer value) {
        return Stream.of(LoginTypeEnum.values())
                .filter(loginTypeEnum -> loginTypeEnum.value.equals(value))
                .findAny()
                .orElse(UNDEFINED);
    }

}