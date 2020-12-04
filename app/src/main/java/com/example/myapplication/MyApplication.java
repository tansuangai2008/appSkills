package com.example.myapplication;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.photoalbum.NimUIKit;
import com.facebook.stetho.Stetho;

/**
 * author : ly
 * date : 2020/9/15 12:01
 * description :
 */
public class MyApplication extends Application {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        NimUIKit.init(this);
    }
}
