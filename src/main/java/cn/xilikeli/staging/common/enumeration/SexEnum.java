package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 性别枚举
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public enum SexEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 男
     */
    MALE(0, "男"),

    /**
     * 女
     */
    FEMALE(1, "女"),

    /**
     * 保密
     */
    SECRECY(2, "保密");

    private final Integer index;

    private final String description;

    SexEnum(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    public Integer getIndex() {
        return this.index;
    }

    public String getDescription() {
        return this.description;
    }

    public static SexEnum toEnum(Integer index) {
        return Stream.of(SexEnum.values())
                .filter(sexEnum -> sexEnum.index.equals(index))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static SexEnum toEnum(String description) {
        return Stream.of(SexEnum.values())
                .filter(sexEnum -> sexEnum.description.equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static String getDescriptionByIndex(Integer index) {
        return Stream.of(SexEnum.values())
                .filter(sexEnum -> sexEnum.index.equals(index))
                .map(SexEnum::getDescription)
                .findAny()
                .orElse("");
    }

    public static List<SexEnum> getAllEffectiveEnum() {
        return Arrays.stream(SexEnum.values())
                .filter(sexEnum -> sexEnum.index > UNDEFINED.index)
                .collect(Collectors.toList());
    }

}