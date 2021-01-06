//package com.trade.eight.net.okhttp
//
//import com.trade.eight.app.MyApplication
//import com.trade.eight.config.AndroidAPIConfig
//import com.trade.eight.dao.DataCacheDao
//import com.trade.eight.tools.NetworkUtils.isNetworkConnected
//import okhttp3.*
//
//
///**
// *  author : ly
// *  date : 2020/10/20 11:59
// *  description : 缓存实现拦截器
// */
//class CacheResponseInterceptor : Interceptor {
//    private val TAG = CacheResponseInterceptor::class.java.simpleName
//    override fun intercept(chain: Interceptor.Chain): Response? {
////        Response response = chain.proceed(chain.request());
//        var request: Request = chain.request()
//        val networkAvail: Boolean = isNetworkConnected(MyApplication.getInstance())
//        if (networkAvail) {
//            return chain.proceed(request)
//        }
//
//        var response: Response? = null
//        if (request.url().toString().contains(AndroidAPIConfig.URL_ADS_4HOMEANDLIVE)) {
//            var dataCacheObj = DataCacheDao.getInstance().queryDataCache(AndroidAPIConfig.URL_ADS_4HOMEANDLIVE)
//            response = Response.Builder()
//                    .request(request)
////                    .priorResponse(stripBody(priorResponse))
//                    .protocol(Protocol.HTTP_1_1)
//                    .code(200)
//                    .message("test")
//                    .body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), dataCacheObj.cacheResponseStr))
//                    .build()
//            return  response
//
//        }
//
//        return chain.proceed(request)
//
//    }
//}