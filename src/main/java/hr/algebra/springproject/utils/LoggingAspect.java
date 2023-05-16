package hr.algebra.springproject.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* hr.algebra.springproject.controller.*.*(..))")
    public void logBefore(final JoinPoint joinPoint) {
        final String methodName = joinPoint.getSignature().getName();
        System.out.println("Executing method: " + methodName);
    }
}
