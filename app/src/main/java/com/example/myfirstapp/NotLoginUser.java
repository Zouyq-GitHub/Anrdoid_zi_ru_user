package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class NotLoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login_user);
        userStorage();
    }

    //用户本地存储方法
    private void userStorage() {
        TextView textView = findViewById(R.id.userStorage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context中的方法openFileOutput()，获取一个FileOutputStream对象
                try {
                    //用MODE_PRIVAT模式，创建一个hxy.txt文件
                    FileOutputStream outputStream = openFileOutput("hxy.txt",Context.MODE_APPEND);
                    //创建一个Usr对象(实现了Serializable接口，来让User对象可以通过流写入)
                    //下面的就没么好说的了，写入方法和冲刷方法
                    outputStream.write("HelloWorld ".getBytes());
                    outputStream.flush();
                    outputStream.write("HelloWorld123 ".getBytes());
                    outputStream.flush();
                    Log.e("文件写入","true");

                    //读取数据

                    //用StringBuilder来接收数据，而不是用String+=的方法。
                    StringBuilder sb = new StringBuilder();
                    //每次读取1024个byte的数据
                    byte[] bytes = new byte[1024];
                    FileInputStream inputStream = openFileInput("hxy.txt");
                    int len = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        sb.append(new String(bytes, 0, len));
                    }
                    Log.e("读取到的数据", sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}