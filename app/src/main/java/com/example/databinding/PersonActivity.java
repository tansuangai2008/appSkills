package com.example.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.datastore.v1.User;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.annotation.AspectAnalyze;
import com.example.myapplication.databinding.ActPersonBinding;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * author : ly
 * date : 2020/12/31 14:19
 * description : databinding 试炼
 */
public class PersonActivity extends BaseActivity {

    private static final String TAG = "PersonActivity";

    private Person person = new Person();

    public static void startAct(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, PersonActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_person);
        ActPersonBinding binding = DataBindingUtil.setContentView(this, R.layout.act_person);
        binding.setPerson(person);
        binding.setMainActivity(this);
        getTagStr("BBBB");

        User.Builder userBuilder = User.newBuilder();
        userBuilder.setName("yyliu");
        userBuilder.setAge(18);
        userBuilder.setIsMarried(true);
        User user = userBuilder.build();
        //序列化
        byte[] data = user.toByteArray();
        Log.e(TAG, "序列化===" + data);
        //反序列化
        try {
            User user1 = User.parseFrom(data);
            Log.e(TAG, "打印出来的值:" + new Gson().toJson(user1));
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

    }

    @AspectAnalyze(name = "setButtonClick")
    public void setButtonClick(View view) {
        person.setFirstName("I am Yang liu");
        person.setLastName("I am Liu Yang");
        person.setAge(28);
    }

    public String getTagStr(String tempStr) {
        return tempStr;
    }
}
