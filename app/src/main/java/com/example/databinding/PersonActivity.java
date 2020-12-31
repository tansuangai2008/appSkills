package com.example.databinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActPersonBinding;

/**
 * author : ly
 * date : 2020/12/31 14:19
 * description : databinding 试炼
 */
public class PersonActivity extends BaseActivity {

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
    }

    public void setButtonClick(View view) {
        person.setFirstName("I am Yang liu");
        person.setLastName("I am Liu Yang");
        person.setAge(28);
    }
}
