package cn.xilikeli.staging.dto.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

/**
 * <p>
 * 枚举校验器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private static final Logger log = LoggerFactory.getLogger(EnumValueValidator.class);

    /**
     * 枚举类
     */
    private Class<?> cls;

    private static final Integer UNDEFINED = -1;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    /**
     * 校验
     *
     * @param value   传入值
     * @param context 上下文
     * @return 是否成功
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        try {
            Object[] objs = cls.getEnumConstants();
            Method method = cls.getMethod("getIndex");
            for (Object obj : objs) {
                Object val = method.invoke(obj);
                if (!val.equals(UNDEFINED) && val.equals(value)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.warn("EnumValue 校验异常: ", e);
            return false;
        }
    }

}