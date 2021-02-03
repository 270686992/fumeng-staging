package cn.xilikeli.staging.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 通过日志监视 service 执行时间
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    @Around("execution(* cn.xilikeli.staging.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetClz = joinPoint.getTarget().getClass();
        String targetMethod = joinPoint.getSignature().getName();

        log.info("======== {}.{} 开始执行 ========", targetClz, targetMethod);
        // 记录开始时间
        long begin = System.currentTimeMillis();
        // 执行目标 service
        Object result = joinPoint.proceed();
        // 记录结束时间
        long end = System.currentTimeMillis();
        // 计算时间差
        long takeTime = end - begin;
        log.info("======== {}.{} 执行结束, 耗时: {} 毫秒 ========", targetClz, targetMethod, takeTime);

        return result;
    }

}