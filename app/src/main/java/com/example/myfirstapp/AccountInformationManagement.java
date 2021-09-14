package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 账号信息管理
 */
public class AccountInformationManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information_management);
        //获取用户id
        Intent intent = getIntent();
        String u_id = intent.getStringExtra("u_id");
        System.out.println("信息页面用户id" + u_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}