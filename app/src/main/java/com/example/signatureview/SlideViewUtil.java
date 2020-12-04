package com.example.signatureview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * @author: zeting
 * @date: 2020/9/16
 */
public class SlideViewUtil {
    public static final String TAG = SlideViewUtil.class.getSimpleName();
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    public File getSaveImgPath(Context context, String userId) {
        File fileName = null;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && (hasExternalStoragePermission(context))) {
            // 获取签名路径
            File filePath = context.getExternalFilesDir("Signature");
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            fileName = new File(filePath, "userId_" + userId + "_" + System.currentTimeMillis() + ".png");
        }
        if (fileName != null) {
            Log.d(TAG, "创建文件：" + fileName.getAbsolutePath());
        }
        return fileName;
    }


    /**
     * 保存图片
     *
     * @param path
     * @param cacheBitmap
     */
    public void saveViewToFile(String path, Bitmap cacheBitmap) {
        try {
            if (TextUtils.isEmpty(path)) {
                return;
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            cacheBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] buffer = bos.toByteArray();
            if (buffer != null) {
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
                OutputStream os = new FileOutputStream(path);
                os.write(buffer);
                os.close();
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断写入权限
     *
     * @param context
     * @return
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
