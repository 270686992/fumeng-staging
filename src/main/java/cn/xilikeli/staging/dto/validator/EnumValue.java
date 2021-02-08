package cn.xilikeli.staging.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * 枚举校验
 * 校验的值必须为 target(枚举类) 中的一项
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, TYPE_USE, TYPE_PARAMETER})
@Constraint(validatedBy = EnumValueValidator.class)
public @interface EnumValue {

    /**
     * 目标值, 必须是一个枚举类
     */
    Class<? extends Enum<?>> target();

    String message() default "value must in enum";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}