package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 登录类型枚举
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
    USERNAME_PASSWORD_LOGIN(0, "用户名、密码登录"),

    /**
     * 微信小程序登录
     */
    WE_CHAT_APPLETS_LOGIN(1, "微信小程序登录"),

    /**
     * 邮箱登录
     */
    EMAIL_LOGIN(2, "邮箱登录"),

    /**
     * 手机号登录
     */
    MOBILE_LOGIN(3, "手机号登录");

    private final Integer index;

    private final String description;

    LoginTypeEnum(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    public Integer getIndex() {
        return this.index;
    }

    public String getDescription() {
        return this.description;
    }

    public static LoginTypeEnum toEnum(Integer index) {
        return Stream.of(LoginTypeEnum.values())
                .filter(loginTypeEnum -> loginTypeEnum.index.equals(index))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static LoginTypeEnum toEnum(String description) {
        return Stream.of(LoginTypeEnum.values())
                .filter(loginTypeEnum -> loginTypeEnum.description.equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static String getDescriptionByIndex(Integer index) {
        return Stream.of(LoginTypeEnum.values())
                .filter(loginTypeEnum -> loginTypeEnum.index.equals(index))
                .map(LoginTypeEnum::getDescription)
                .findAny()
                .orElse("");
    }

    public static List<LoginTypeEnum> getAllEffectiveEnum() {
        return Arrays.stream(LoginTypeEnum.values())
                .filter(loginTypeEnum -> loginTypeEnum.index > UNDEFINED.index)
                .collect(Collectors.toList());
    }

}