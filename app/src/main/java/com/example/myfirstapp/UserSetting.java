package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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
            LinearLayout layout = findViewById(R.id.notLogin);
            layout.setVisibility(View.VISIBLE);
        }
    }
}