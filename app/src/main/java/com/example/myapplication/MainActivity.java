package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nightmode.NightModeActivity;
import com.example.photoalbum.common.media.imagepicker.Constants;
import com.example.photoalbum.common.media.imagepicker.ImagePickerLauncher;
import com.example.photoalbum.common.media.model.GLImage;
import com.example.recyclehelper.ui.RecycleViewHelperActivity;
import com.example.signature.ui.SignatureViewAct;
import com.example.signatureview.SlideViewActivity;
import com.example.view.ui.MyCustomViewActivity;
import com.example.room.ui.RoomUseActivity;

import java.util.ArrayList;

/**
 * demo 入口主页
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PICK_AVATAR_REQUEST = 0x0E;


    private View ll_use_room = null;
    private View ll_diy_view = null;
    private View ll_signature_view = null;
    private View ll_recycle_helper = null;
    private View ll_night_mode = null;
    private View ll_photo_album = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_use_room = findViewById(R.id.ll_use_room);
        ll_use_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RoomUseActivity.class);
                startActivity(intent);
            }
        });
        ll_diy_view = findViewById(R.id.ll_diy_view);
        ll_diy_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyCustomViewActivity.class);
                startActivity(intent);
            }
        });
        ll_signature_view = findViewById(R.id.ll_signature_view);
        ll_signature_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, SignatureViewAct.class);
                intent.setClass(MainActivity.this, SlideViewActivity.class);
                startActivity(intent);
            }
        });
        ll_recycle_helper = findViewById(R.id.ll_recycle_helper);
        ll_recycle_helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, SignatureViewAct.class);
                intent.setClass(MainActivity.this, RecycleViewHelperActivity.class);
                startActivity(intent);
            }
        });
        ll_night_mode = findViewById(R.id.ll_night_mode);
        ll_night_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NightModeActivity.class);
                startActivity(intent);
            }
        });
        ll_photo_album = findViewById(R.id.ll_photo_album);
        ll_photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePickerLauncher.pickImage(MainActivity.this, PICK_AVATAR_REQUEST, R.string.set_head_image);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_AVATAR_REQUEST) {
            Log.e(TAG, "获取到图片路径====");
            onPicked(data);
        }


    }

    private void onPicked(Intent data) {
        if (data == null) {
            return;
        }
        ArrayList<GLImage> images = (ArrayList<GLImage>) data.getSerializableExtra(Constants.EXTRA_RESULT_ITEMS);
        if (images == null || images.isEmpty()) {
            return;
        }
        GLImage image = images.get(0);
        Log.e(TAG, "获取选择图片路径===" + image.getPath());
    }
}