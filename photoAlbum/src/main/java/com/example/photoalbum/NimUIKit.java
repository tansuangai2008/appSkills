package com.example.photoalbum;

import android.content.Context;

/**
 * 云信UI组件接口/定制化入口
 * Created by huangjun on 2017/9/29.
 */

public class NimUIKit {

    /**
     * 初始化UIKit
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        NimUIKitImpl.init(context);
    }

    /**
     * 初始化UIKit,
     *
     * @param context 上下文
     * @param option  自定义选项
     */
    public static void init(Context context, UIKitOptions option) {
        NimUIKitImpl.init(context, option);
    }


    /**
     * 获取上下文
     *
     * @return 必须初始化后才有值
     */
    public static Context getContext() {
        return NimUIKitImpl.getContext();
    }


}
