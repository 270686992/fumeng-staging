package cn.xilikeli.staging.dto.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.Field;

/**
 * <p>
 * EqualField 注解的校验器类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 16:53
 * @since JDK1.8
 */
@Slf4j
public class EqualFieldValidator implements ConstraintValidator<EqualField, Object> {
    /**
     * 源属性
     */
    private String srcField;

    /**
     * 目标属性
     */
    private String dstField;

    @Override
    public void initialize(EqualField constraintAnnotation) {
        this.srcField = constraintAnnotation.srcField();
        this.dstField = constraintAnnotation.dstField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Class<?> clazz = o.getClass();

        Field srcField = ReflectionUtils.findField(clazz, this.srcField);
        Field dstField = ReflectionUtils.findField(clazz, this.dstField);

        try {
            if (srcField == null || dstField == null) {
                throw new ValidationException("反射获取变量失败");
            }

            srcField.setAccessible(true);
            dstField.setAccessible(true);
            Object src = srcField.get(o);
            Object dst = dstField.get(o);

            // 其中一个变量为 null 时，则必须两个都为 null 才相等
            if (src == null || dst == null) {
                return src == dst;
            }

            // 如果两个对象内存地址相同，则一定相等
            if (src == dst) {
                return true;
            }

            // 调用 equals 方法比较
            return src.equals(dst);
        } catch (Exception e) {
            log.warn("EqualFieldValidator 校验异常: ", e);
            return false;
        }
    }
}