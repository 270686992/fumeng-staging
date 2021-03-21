package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 注册类型枚举
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/3/21
 * @since JDK1.8
 */
public enum RegisterTypeEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 用户名、密码注册
     */
    USERNAME_PASSWORD_REGISTER(0, "用户名、密码登录"),

    /**
     * 微信小程序注册
     */
    WE_CHAT_APPLETS_REGISTER(1, "微信小程序注册"),

    /**
     * 邮箱注册
     */
    EMAIL_REGISTER(2, "邮箱注册"),

    /**
     * 手机号注册
     */
    MOBILE_REGISTER(3, "手机号注册");

    private final Integer index;

    private final String description;

    RegisterTypeEnum(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    public Integer getIndex() {
        return this.index;
    }

    public String getDescription() {
        return this.description;
    }

    public static RegisterTypeEnum toEnum(Integer index) {
        return Stream.of(RegisterTypeEnum.values())
                .filter(registerTypeEnum -> registerTypeEnum.index.equals(index))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static RegisterTypeEnum toEnum(String description) {
        return Stream.of(RegisterTypeEnum.values())
                .filter(registerTypeEnum -> registerTypeEnum.description.equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static String getDescriptionByIndex(Integer index) {
        return Stream.of(RegisterTypeEnum.values())
                .filter(registerTypeEnum -> registerTypeEnum.index.equals(index))
                .map(RegisterTypeEnum::getDescription)
                .findAny()
                .orElse("");
    }

    public static List<RegisterTypeEnum> getAllEffectiveEnum() {
        return Arrays.stream(RegisterTypeEnum.values())
                .filter(registerTypeEnum -> registerTypeEnum.index > UNDEFINED.index)
                .collect(Collectors.toList());
    }

}