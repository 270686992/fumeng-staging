package cn.xilikeli.staging.common.enumeration;

/**
 * <p>
 * 用户状态枚举类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public enum AccountStatusEnum {

    /**
     * 冻结
     */
    BLOCK(0, "冻结"),

    /**
     * 正常
     */
    NORMAL(1, "正常");


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
    AccountStatusEnum(Integer value, String description) {
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