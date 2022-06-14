package com.example.myruler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;

/**
 * author : liuyang
 * date   : 2022/6/13
 * desc   :
 */
public class MyRulerViewActivity extends BaseActivity {

    public static void startAct(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyRulerViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_my_ruler);
    }
}
