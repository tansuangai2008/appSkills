package com.example.storage

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.act_storage.*
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.concurrent.thread

/**
 *  author : ly
 *  date : 2020/12/9 15:22
 *  description : android 10 读取文件适配
 */
const val PICK_FILE = 1

class AlbumActivity : BaseActivity() {

    companion object {
        private val TAG = AlbumActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, AlbumActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_storage)
        val permissionsToRequire = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequire.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequire.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionsToRequire.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequire.toTypedArray(), 0)
        }


        browseAlbum.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AlbumBrowerActivity.startAct(this@AlbumActivity)
            }
        })
        addImageToAlbum.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.storage_test_image)
                val displayName = "${System.currentTimeMillis()}.jpg"
                val mimeType = "image/jpeg"
                val compressFormat = Bitmap.CompressFormat.JPEG
                addBitmapToAlbum(bitmap, displayName, mimeType, compressFormat)
            }

        })

        pickFile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                pickFileAndCopyUriToExternalFilesDir()
            }

        })

    }

    private fun addBitmapToAlbum(bitmap: Bitmap, displayName: String, mimeType: String, compressFormat: Bitmap.CompressFormat) {
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
        values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
        } else {
            values.put(MediaStore.MediaColumns.DATA, "${Environment.getExternalStorageDirectory().path}/${Environment.DIRECTORY_DCIM}/$displayName")
        }
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        if (uri != null) {
            val outputStream = contentResolver.openOutputStream(uri)
            if (outputStream != null) {
                bitmap.compress(compressFormat, 100, outputStream)
                outputStream.close()
                Toast.makeText(this, "Add bitmap to album succeeded.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickFileAndCopyUriToExternalFilesDir() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        startActivityForResult(intent, PICK_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_FILE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val uri = data.data
                    if (uri != null) {
                        val fileName = getFileNameByUri(uri)
                        copyUriToExternalFilesDir(uri, fileName)
                    }
                }
            }
        }
    }

    private fun getFileNameByUri(uri: Uri): String {
        var fileName = System.currentTimeMillis().toString()
        val cursor = contentResolver.query(uri, null, null, null, null)
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
            cursor.close()
        }
        return fileName
    }

    private fun copyUriToExternalFilesDir(uri: Uri, fileName: String) {
        thread {
            val inputStream = contentResolver.openInputStream(uri)
            val tempDir = getExternalFilesDir("temp")
            if (inputStream != null && tempDir != null) {
                val file = File("$tempDir/$fileName")
                val fos = FileOutputStream(file)
                val bis = BufferedInputStream(inputStream)
                val bos = BufferedOutputStream(fos)
                val byteArray = ByteArray(1024)
                var bytes = bis.read(byteArray)
                while (bytes > 0) {
                    bos.write(byteArray, 0, bytes)
                    bos.flush()
                    bytes = bis.read(byteArray)
                }
                bos.close()
                fos.close()
                runOnUiThread {
                    Toast.makeText(this, "Copy file into $tempDir succeeded.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You must allow all the permissions.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    }
}