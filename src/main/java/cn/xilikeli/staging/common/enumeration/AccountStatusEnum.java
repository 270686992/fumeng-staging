package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 冻结
     */
    BLOCK(0, "冻结"),

    /**
     * 正常
     */
    NORMAL(1, "正常");

    private final Integer index;

    private final String description;

    AccountStatusEnum(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    public Integer getIndex() {
        return this.index;
    }

    public String getDescription() {
        return this.description;
    }

    public static AccountStatusEnum toEnum(Integer index) {
        return Stream.of(AccountStatusEnum.values())
                .filter(accountStatusEnum -> accountStatusEnum.index.equals(index))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static AccountStatusEnum toEnum(String description) {
        return Stream.of(AccountStatusEnum.values())
                .filter(accountStatusEnum -> accountStatusEnum.description.equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static String getDescriptionByIndex(Integer index) {
        return Stream.of(AccountStatusEnum.values())
                .filter(accountStatusEnum -> accountStatusEnum.index.equals(index))
                .map(AccountStatusEnum::getDescription)
                .findAny()
                .orElse("");
    }

    public static List<AccountStatusEnum> getAllEffectiveEnum() {
        return Arrays.stream(AccountStatusEnum.values())
                .filter(accountStatusEnum -> accountStatusEnum.index > UNDEFINED.index)
                .collect(Collectors.toList());
    }

}