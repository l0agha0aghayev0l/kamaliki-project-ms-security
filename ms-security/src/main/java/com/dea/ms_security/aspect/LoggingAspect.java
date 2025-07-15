package com.dea.ms_security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    
    @Around("execution(* com.dea.ms_security.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Execution time: {} ms in {}", end - start, joinPoint.getSignature());
        return proceed;
    }

    @AfterThrowing(pointcut = "execution(* com.dea.ms_security.service.*.*(..))", throwing = "ex")
    public void logException(ProceedingJoinPoint joinPoint, Throwable ex) {
        log.error("Exception {} in {}", ex.getMessage(), joinPoint.getSignature());
    }
}
