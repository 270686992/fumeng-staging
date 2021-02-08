package cn.xilikeli.staging.common.enumeration;

/**
 * <p>
 * 性别枚举类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public enum SexEnum {

    /**
     * 女
     */
    FEMALE(0, "女"),

    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 保密
     */
    SECRECY(2, "保密");

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
    SexEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}