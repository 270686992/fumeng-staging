package cn.xilikeli.staging.common.aop;

import cn.xilikeli.staging.common.configuration.CodeMessageConfiguration;
import cn.xilikeli.staging.vo.UnifyResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 处理返回结果为 UnifyResponseVO 的 Controller
 * 如有在配置文件中配置了相应的 code->message, 在此处通过 code 设置为配置文件中配置的对应消息
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/22 - 01:43
 * @since JDK1.8
 */
@Aspect
@Component
public class ResultAspect {
    @AfterReturning(returning = "result", pointcut = "execution(public * cn.xilikeli.staging.controller..*.*(..))")
    public void doAfterReturning(UnifyResponseVO result) {
        Integer code = result.getCode();
        // code-message.properties 中配置的 message
        String message = CodeMessageConfiguration.getMessageByCode(code);

        // 如果 code-message.properties 中指定了 message, 则使用指定的 message
        if (StringUtils.isNotBlank(message)) {
            result.setMessage(message);
        }
    }
}