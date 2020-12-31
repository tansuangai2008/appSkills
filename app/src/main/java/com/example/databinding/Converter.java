package com.example.databinding;

import androidx.databinding.InverseMethod;

import com.example.photoalbum.common.util.string.StringUtil;

/**
 * author : ly
 * date : 2020/12/31 14:25
 * description :
 */
public class Converter {
    @InverseMethod("toInt")
    public static String toString(int value) {
        return "" + value;
    }

    public static int toInt(String value) {
        if(StringUtil.isEmpty(value)){
            value = "0";
        }
        return Integer.parseInt(value);
    }
}
