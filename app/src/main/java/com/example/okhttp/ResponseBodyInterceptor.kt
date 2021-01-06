package com.trade.eight.net.okhttp

import com.common.lib.util.Log
import com.trade.eight.app.MyApplication
import com.trade.eight.tools.NetworkUtils
import com.trade.eight.tools.NetworkUtils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.BufferedSource
import org.json.JSONObject
import java.nio.charset.Charset


/**
 *  author : ly
 *  date : 2020/10/20 11:59
 *  description :
 */
class ResponseBodyInterceptor : Interceptor {
    private val TAG = ResponseBodyInterceptor::class.java.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {
//        Response response = chain.proceed(chain.request());
        var response = chain.proceed(chain.request())
        val networkAvail: Boolean = isNetworkAvailable(MyApplication.getInstance())
        if(!networkAvail){
            Log.e(TAG, "LLLLLLLLLLLLLLLLLLL")
        }
//        String contentType = response.header("Content-Type");
//        if (TextUtils.isEmpty(contentType)) contentType = "application/json";
//        var contentType: String? = response?.header("Content-Type")
//        if (TextUtils.isEmpty(contentType)) {
//            contentType = "application/json"
//        }
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        String result = buffer.clone().readString(charset);
        response.body()?.let {
            var contentType: String? = response.header("Content-Type")
            Log.e(TAG, "$contentType")
            Log.e(TAG, "MediaType= ${it.contentType()}")
            val source: BufferedSource = it.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val buffer: Buffer = source.buffer()
            val result = buffer.clone().readString(Charset.defaultCharset())
            val responseStr: String = result
            if (contentType!!.contains("application/json")) {
                var jsonAll = JSONObject(responseStr)
                var jsonData:String = jsonAll.optString("data")
//                Log.e(TAG, "${response.request().url()}==$responseStr")
                Log.e(TAG, "${response.request().url()}==$jsonData")
            }

        }

//        val responseBody:ResponseBody? = response?.body()
//        val responseStr:String =  Gson().toJson(response.body()?.string())
//        val responseStr: String? = responseBody?.string()
//        response = response.newBuilder().body(ResponseBody.create(MediaType.parse(contentType), responseStr)).build()
        return response

    }
}