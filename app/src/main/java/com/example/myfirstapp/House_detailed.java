package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myfirstapp.entity.House;
import com.example.myfirstapp.util.UpdateImg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class House_detailed extends AppCompatActivity {
//    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detailed);
        //接收数据
        Intent intent = getIntent();
        Bundle house = intent.getExtras();
//        System.out.println(house.getString("u_id"));
//        System.out.println(house.getString("u_image"));
//        System.out.println(house.getString("h_price"));
//        System.out.println(house.getString("h_space"));
//        System.out.println(house.getString("h_type"));
//        System.out.println(house.getString("h_address"));
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
        //监听视频开关
        Switch s = findViewById(R.id.house_video);
        //视频控件
        VideoView videoView = findViewById(R.id.house_video_src);
        //默认视频关闭控件
        videoView.setVisibility(View.GONE);
        //实例化图片控件
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //隐藏图片显示
                    imageView.setVisibility(View.GONE);
                    //显示视频界面
                    videoView.setVisibility(View.VISIBLE);
                    //视频播放
                    //加载指定的视频文件
                    String path = "http://192.168.85.173:8080/public/lenveo.mp4";
                    //让VideoView获取焦点
//                    MediaController mediaController = new MediaController(this);
                    videoView.setVideoPath(path);
                    videoView.requestFocus();
                    videoView.start();

                } else {
                    //切换图片展示
                    imageView.setVisibility(View.VISIBLE);
                    //隐藏视频界面
                    videoView.setVisibility(View.GONE);
                }
            }
        });
    }
}