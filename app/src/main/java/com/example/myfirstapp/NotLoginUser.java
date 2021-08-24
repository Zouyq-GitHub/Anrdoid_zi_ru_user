package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileInputStream;


public class NotLoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login_user);
        userStorage();
        gotoLoginPage();
    }

    //用户消息通知
    private void userStorage() {
        ImageView imageView = findViewById(R.id.xiao_xi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用方法判断用户登录状态 登录跳消息 未登录跳登录
                boolean loginState = loginState();
//                if (loginState) {
//                    Intent intent = new Intent(NotLoginUser.this, test_interaction.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(NotLoginUser.this, ZiRuLogin.class);
//                    startActivity(intent);
//                }
            }
        });
    }

    //用户跳转登录
    private void gotoLoginPage() {
        // 点击登录-注册
        TextView textView = findViewById(R.id.userStorage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
//                Intent intent = new Intent(NotLoginUser.this, ZiRuLogin.class);
//                startActivity(intent);

                //test 试试实体类
                TestEntity testEntity = new TestEntity();
                testEntity.init();
            }
        });
    }

    //验证是否登录方法  判断用户登录状态
    public boolean loginState() {
        //验证用户是否是登录状态:
        //读取数据:
        try {
            //用StringBuilder来接收数据，而不是用String+=的方法。
            StringBuilder sb = new StringBuilder();
            //每次读取1024个byte的数据
            byte[] bytes = new byte[1024];
            FileInputStream inputStream = openFileInput("login.txt");
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            Log.e("读取到的数据", sb.toString());
            //进行登录验证
            if (sb.toString() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}