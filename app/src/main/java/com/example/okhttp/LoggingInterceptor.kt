package com.trade.eight.net.okhttp

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  author : ly
 *  date : 2020/10/20 10:11
 *  description :
 */
class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        //1.请求前--打印请求信息
        var t1:Long = System.nanoTime()
        Log.e("LoggingInterceptor",String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))
        //2.网络请求
        var response = chain.proceed(request)
        //网络响应后--打印响应信息
        var t2:Long = System.nanoTime()
        Log.e("LoggingInterceptor", java.lang.String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))
        return response
    }
}