package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.entity.User;

import java.io.FileOutputStream;

public class UserSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        LogOut();
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

    //退出登录逻辑
    private void LogOut(){
        //获取文本ID触发点击事件
        TextView textView = findViewById(R.id.LogOut);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击退出登录","true");
                try {
                    //清空文档
                    FileOutputStream outputStream = openFileOutput("login.txt", Context.MODE_PRIVATE);
                    //数据录入-刷新
                    outputStream.write("".getBytes());
                    outputStream.flush();
                    //页面跳用户主页
                    Intent intent = new Intent(UserSetting.this, NotLoginUser.class);
                    //跳转
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}