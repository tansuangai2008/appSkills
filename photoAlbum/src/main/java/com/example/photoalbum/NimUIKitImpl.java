package com.example.photoalbum;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.photoalbum.common.util.storage.StorageType;
import com.example.photoalbum.common.util.storage.StorageUtil;
import com.example.photoalbum.common.util.sys.ScreenUtil;


/**
 * UIKit能力实现类。
 */
public final class NimUIKitImpl {

    // context
    private static Context context;
    private static UIKitOptions options;


    /*
     * ****************************** 初始化 ******************************
     */
    public static void init(Context context) {
        init(context, new UIKitOptions());
    }


    public static void init(Context context, UIKitOptions options) {
        NimUIKitImpl.context = context.getApplicationContext();
        NimUIKitImpl.options = options;
        // init tools
        StorageUtil.init(context, options.appCacheDir);
        ScreenUtil.init(context);
        // init log
        String path = StorageUtil.getDirectoryByDirType(StorageType.TYPE_LOG);
    }

    /*
    * ****************************** basic ******************************
    */
    public static Context getContext() {
        return context;
    }


}
