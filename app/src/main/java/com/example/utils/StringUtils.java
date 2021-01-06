package com.example.utils;

/**
 * author : ly
 * date : 2021/1/6
 * description :
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if (null == str)
            return true;
        if (str.trim().length() == 0)
            return true;
        if (str.trim().equalsIgnoreCase("null"))
            return true;
        return false;
    }

    /**
     * 判断该字符串是否为中文
     *
     * @param string
     * @return
     */
    private static boolean isChinese(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            if (19968 <= n && n < 40869) {
                //只要有中文字符，就认定是中文
                return true;
            }
        }
        return false;
    }

    public static String handleText(String str) {
        if (isEmpty(str)) {
            return "";
        }
        if (isChinese(str)) {
            //包含中文只展示8位
            if (str.length() <= 8) {
                return str;
            } else {
                return str.substring(0, 8) + "...";
            }
        } else {
            //其它语言展示16位
            if (str.length() <= 16) {
                return str;
            } else {
                return str.substring(0, 16) + "...";
            }
        }
    }


    /**
     * 此方法是中文和其它语言同时考虑在内的情况
     *
     * @param str    需要处理的字符串
     * @param maxLen 限制的长度
     */
    public static String handleText(String str, int maxLen) {
        if (isEmpty(str)) {
            return "";
        }
        int count = 0;
        int endIndex = 0;
        boolean isChinese;
        for (int i = 0; i < str.length(); i++) {
            char item = str.charAt(i);
            if (19968 <= item && item < 40869) {
                count = count + 2;
                isChinese = true;
            } else {
                count = count + 1;
                isChinese = false;
            }
            endIndex = i;
            if (maxLen == count) {
                break;
            } else if (maxLen < count) {
                if (isChinese) {
                    endIndex = --i;
                    //中文占了两个长度，所以减一
                }
                break;
            } else {

            }

        }
        if (endIndex == str.length() - 1) {
            return str;
        } else {
            return str.substring(0, endIndex + 1) + "...";
        }

    }
}
