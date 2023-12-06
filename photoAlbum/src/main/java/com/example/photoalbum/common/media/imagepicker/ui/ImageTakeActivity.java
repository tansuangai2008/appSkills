package com.example.photoalbum.common.media.imagepicker.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;


import com.example.photoalbum.R;
import com.example.photoalbum.common.media.Extras;
import com.example.photoalbum.common.media.imagepicker.Constants;
import com.example.photoalbum.common.media.imagepicker.ImagePicker;
import com.example.photoalbum.common.media.imagepicker.ImagePickerLauncher;
import com.example.photoalbum.common.media.imagepicker.Utils;
import com.example.photoalbum.common.media.model.GLImage;
import com.example.photoalbum.common.media.model.GenericFileProvider;
import com.example.photoalbum.common.util.sys.TimeUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.photoalbum.common.media.imagepicker.Constants.REQUEST_CODE_CROP;
import static com.example.photoalbum.common.media.imagepicker.Constants.REQUEST_PERMISSION_CAMERA;

/**
 *
 */

public class ImageTakeActivity extends ImageBaseActivity {
    private static final String TAG = "ImageTakeActivity";
    public static final int RESULT_OK_IMAGE = 995;
    private ImagePicker imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePicker = ImagePicker.getInstance();
        if (savedInstanceState == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (!(checkPermission(Manifest.permission.CAMERA)) || !(checkPermission(Manifest.permission.READ_MEDIA_IMAGES))) {
                    //Android 13逻辑
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.READ_MEDIA_IMAGES,
                            Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);

                    Log.d("TAG", "android 13  请求");
                } else {
                    takePicture();
                }
            } else {
                if (!(checkPermission(Manifest.permission.CAMERA))
                        || !(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                        || !(checkPermission(Manifest.permission.READ_MEDIA_IMAGES))) {
                    ActivityCompat.requestPermissions(this, new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA},
                            REQUEST_PERMISSION_CAMERA);
                } else {
                    takePicture();
                }
            }
        }
    }

    @Override
    public void clearRequest() {
    }

    @Override
    public void clearMemoryCache() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            boolean grantFail = false;
            for (int grant : grantResults) {
                Log.d("TAG", "android 13 授权 (0：允许，-1：拒绝)：" + grant);
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    grantFail = true;
                }
            }
            if (!grantFail) {
//                ImagePickerLauncher.takePicture(this, Constants.REQUEST_CODE_TAKE, imagePicker.getOption());
//                finish();
                takePicture();
            } else {
                showToast("Permission is forbidden, unable to open camera");
                finish();
            }
        }
    }

    private void takePicture() {
        File takeImageFile = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            if (Utils.existSDCard()) {
                Log.e("takephoto", "Utils.existSDCard()==true");
                takeImageFile = new File(Environment.getExternalStorageDirectory(), "/DCIM/camera/");
            } else {
                Log.e("takephoto", "Utils.existSDCard()==false");
                takeImageFile = Environment.getDataDirectory();
            }
            takeImageFile = Utils.createFile(takeImageFile, "IMG_", ".jpg");
            Log.e("takephoto", takeImageFile.getAbsolutePath());
            if (takeImageFile != null) {
                // 默认情况下，即不需要指定intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                // 照相机有自己默认的存储路径，拍摄的照片将返回一个缩略图。如果想访问原始图片，
                // 可以通过dat extra能够得到原始图片位置。即，如果指定了目标uri，data就没有数据，
                // 如果没有指定uri，则data就返回有数据！
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //Android N必须使用这种方式
                    Uri photoURI = GenericFileProvider.getUriForFile(this, getApplicationContext().getPackageName() +
                            ".generic.file.provider", takeImageFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                } else {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(takeImageFile));
                }
            }
        }
        if (takeImageFile == null) {
            //TODO
            return;
        }
        //FIXME
        imagePicker.setTakeImageFile(takeImageFile);
        startActivityForResult(takePictureIntent, Constants.REQUEST_CODE_TAKE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_TAKE) {
            if (resultCode == RESULT_OK) {
                if (imagePicker != null && imagePicker.getTakeImageFile() != null) {
                    //发送广播通知图片增加了
                    Utils.galleryAddPic(ImageTakeActivity.this, imagePicker.getTakeImageFile());
                    GLImage glImage = GLImage.Builder.newBuilder().setAddTime(TimeUtil.getNow_millisecond()).setPath(
                            imagePicker.getTakeImageFile().getAbsolutePath()).setMimeType("image/jpeg").build();
                    imagePicker.clearSelectedImages();
                    imagePicker.addSelectedImageItem(glImage, true);
                    if (imagePicker.isCrop()) {
                        Intent intent = new Intent(this, ImageCropActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_CROP);  //单选需要裁剪，进入裁剪界面
                        return;
                    } else if (imagePicker.isMultiMode()) {
                        Serializable selectPhotos = getIntent().getSerializableExtra("selectPhotos");
                        ArrayList<GLImage> selectedImages = imagePicker.getSelectedImages();
                        if (selectPhotos != null && selectPhotos instanceof List<?> && ((List<?>) selectPhotos).size() > 0) {
                            selectedImages.clear();
                            //已选择并传给上个页面后 又回传过来的数据 目前只有圈子发布帖子有在用
                            List<String> selectPhotosList = (List<String>) selectPhotos;
                            Log.e(TAG, selectPhotosList.toString());
                            for (int i = 0; i < selectPhotosList.size(); i++) {
                                GLImage glImage1 = new GLImage(selectPhotosList.get(i));
                                selectedImages.add(glImage1);
                            }
                            selectedImages.add(glImage);
                        }
                        Intent intent = new Intent();
                        intent.putExtra(Constants.EXTRA_RESULT_ITEMS, selectedImages);
                        setResult(RESULT_OK_IMAGE, intent);
                        Log.e(TAG, selectedImages.size() + "");
                        finish();
                    } else {
                        Intent intent = new Intent();
//                    intent.putExtra(Constants.EXTRA_RESULT_ITEMS, imagePicker.getSelectedImages());
                        intent.putExtra(Extras.EXTRA_FILE_PATH, imagePicker.getSelectedImages().get(0).getPath());
                        setResult(RESULT_OK_IMAGE, intent);
                        finish();
                    }
                }
            }
            finish();
        } else if (requestCode == REQUEST_CODE_CROP) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK_IMAGE, data);
                finish();
            }
            finish();
        }
    }
}
