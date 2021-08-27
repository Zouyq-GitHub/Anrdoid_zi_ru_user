package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class UserSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //判断用户登录状态 接受数据
        Intent intent = getIntent();
        String loginState = intent.getStringExtra("loginState");
        System.out.println(loginState);
        if (loginState != null && loginState.equals("true")) {
            //有退出登录选项
            Log.e("loginButton", "yes");
        } else {
            //无选项
            Log.e("loginButton", "no");
        }

    }
}