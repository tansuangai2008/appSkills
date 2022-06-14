package com.example.myapplication;

import com.example.myapplication.annotation.AspectAnalyze;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * author : liuyang
 * date   : 2022/1/20
 * desc   :
 */
public interface AspectTraceListener {

    void logger(String tag, String message);

    void onAspectAnalyze(ProceedingJoinPoint joinPoint, AspectAnalyze aspectAnalyze, MethodSignature methodSignature, long duration);
}
