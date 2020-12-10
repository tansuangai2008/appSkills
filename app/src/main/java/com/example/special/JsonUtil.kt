package com.example.special

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * author : ly
 * date : 2020/10/19 15:45
 * description :
 */
object JsonUtil {
    private val gson = Gson()

    /**
     * 将object对象转成json格式字符串
     */
    fun toJson(`object`: Any?): String {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setPrettyPrinting()
        val gson = gsonBuilder.create()
        return gson.toJson(`object`)
    }

    fun <T> objectToJsonString(obj: T): String {
        return gson.toJson(obj)
    }

    fun <T> getBean(jsonString: String?, cls: Class<T>?): T {
        return gson.fromJson(jsonString, cls)
    }

    fun <T> getListData(jsonString: String?, cls: Class<T>?): List<T>? {
        var list: List<T>? = null
        try {
            list = gson.fromJson(jsonString, object : TypeToken<List<T>?>() {}.type)
        } catch (e: Exception) {
        }
        return list
    }

    fun <T> GsonToList(gsonString: String?, cls: Class<T>?): List<T>? {
        var list: List<T>? = null
        val gson = Gson()
        if (gson != null) {
            list = gson.fromJson(gsonString, object : TypeToken<List<T>?>() {}.type)
        }
        return list
    }

    fun <T> jsonToList(json: String?, cls: Class<T>?): List<T> {
        val gson = Gson()
        val list: MutableList<T> = ArrayList()
        val array = JsonParser().parse(json).asJsonArray
        for (elem in array) {
            list.add(gson.fromJson(elem, cls))
        }
        return list
    }
}