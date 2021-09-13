package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.entity.House;
import com.example.myfirstapp.util.UpdateImg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class House_detailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detailed);
        //接收数据
        Intent intent = getIntent();
        Bundle house = intent.getExtras();
        System.out.println(house.getString("u_id"));
        System.out.println(house.getString("u_image"));
        System.out.println(house.getString("h_price"));
        System.out.println(house.getString("h_space"));
        System.out.println(house.getString("h_type"));
        System.out.println(house.getString("h_address"));
        //数据更新
        TextView textView = findViewById(R.id.house_address);
        TextView textView1 = findViewById(R.id.house_price);
        ImageView imageView = findViewById(R.id.house_images);
        //修改顶部名称
        textView.setText(house.getString("h_address"));
        //改价格
        String price = house.getString("h_price");
        textView1.setText(price + "(季付价)");
        //图片
        UpdateImg updateImg = new UpdateImg();
        //赋值
        updateImg.imageView = imageView;
        //实现方法传入图片地址
        updateImg.bySrcUpdateImg(house.getString("u_image"));
    }
}