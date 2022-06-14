package com.example.myapplication;

import com.example.myapplication.annotation.AspectAnalyze;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * author : liuyang
 * date   : 2022/1/20
 * desc   :
 */
@Aspect
public class AspectTrace {
    private static AspectTraceListener aspectTraceListener;

        @Pointcut("execution(@com.example.myapplication.annotation.AspectAnalyze * *(..))")
//    @Pointcut(value = "@annotation(com.example.myapplication.annotation.AspectAnalyze)")
    public void aspectAnalyzeAnnotation() {

    }

//    @Pointcut("execution(@com.example.myapplication.annotation.AspectDebugLog **(..))")
//    public void aspectDebugLogAnnotation() {
//
//    }

    @Around("aspectAnalyzeAnnotation()")
    public void aroundJoinAspectAnalyze(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AspectAnalyze aspectAnalyze = methodSignature.getMethod().getAnnotation(AspectAnalyze.class);
        long startTimeMillis = System.currentTimeMillis();
        joinPoint.proceed();
        System.out.println("PPPPPPPPPP");
        if (aspectTraceListener != null) {
            aspectTraceListener.onAspectAnalyze(joinPoint, aspectAnalyze, methodSignature, System.currentTimeMillis() - startTimeMillis);
        }
    }


    public static void setAspectTraceListener(AspectTraceListener aspectTraceListener) {
        AspectTrace.aspectTraceListener = aspectTraceListener;
    }
}
