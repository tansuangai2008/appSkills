package com.example.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * author : LIU YANG
 * date   : 2023/12/6
 * desc   :
 */
public class ImagePathHelper {
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver = context.getContentResolver();

        try (Cursor cursor = contentResolver.query(contentUri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                return cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            Log.e("ImagePathHelper", "Error getting image path: " + e.getMessage());
        }

        return null;
    }
}
