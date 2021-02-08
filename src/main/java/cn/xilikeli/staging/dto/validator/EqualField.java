package cn.xilikeli.staging.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * 比较两个属性是否相等的自定义校验注解
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = EqualFieldValidator.class)
public @interface EqualField {

    /**
     * 源属性
     */
    String srcField();

    /**
     * 目标属性
     */
    String dstField();

    String message() default "the two fields must be equal.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}