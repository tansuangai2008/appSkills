package com.example.myapplication;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author : ly
 * date : 2020/1/17 11:53
 * description : 尝试android AOP
 */
@Aspect
public class AopTest {
    private static final String TAG = AopTest.class.getSimpleName();

    @Pointcut("execution(* com.example.databinding.PersonActivity.getTagStr(..))")
    public void methodGetResult() {

    }

    /**
     * 后置通知，切点之后执行
     *
     * @throws Throwable
     */
    @AfterReturning(pointcut = "methodGetResult()" , returning = "result")
    public void logAfter(JoinPoint point, String result,ProceedingJoinPoint proceedingJoinPoint) {
        Log.e(TAG, "logAfter==" + point.getSignature().toString());
        Log.e(TAG, "logAfter==" + result);
    }

    @Around("execution(* com.example.myapplication.MainActivity.getTagStr(..))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        try {
            if("test android AOP".equals("test android AOP")){
                Log.e(TAG,"interesting interesting ");
                result =  "what your name!!!";
               proceedingJoinPoint.proceed();
            }else {
                result = proceedingJoinPoint.proceed();
            }
            Log.e(TAG, "final final final final !!!");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;

    }

}
