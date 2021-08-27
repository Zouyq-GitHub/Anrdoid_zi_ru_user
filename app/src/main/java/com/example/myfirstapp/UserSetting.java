package com.example.myfirstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.entity.User;
import com.example.myfirstapp.util.Notice;

import java.io.FileOutputStream;

public class UserSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        LogOut();
        userInformation();
        accountInformation();
        communityActivity();
        general();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //调用方法判断用户登录状态 接受数据
        if (loginState()) {
            //有退出登录选项
            LinearLayout layout = findViewById(R.id.notLogin);
            layout.setVisibility(View.VISIBLE);
        }
    }

    //退出登录逻辑
    private void LogOut() {
        //获取文本ID触发点击事件
        TextView textView = findViewById(R.id.LogOut);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出框让用户进行选择是否退出  Dialog弹出框
                //自定义样式
                //View view1 = View.inflate(UserSetting.this, R.layout.activity_layout_dialog, null);
                AlertDialog alertDialog2 = new AlertDialog.Builder(UserSetting.this)
                        .setTitle("提示")
                        .setMessage("是否退出当前账户")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //文档数据页面跳转操作
                                try {
                                    //清空文档
                                    FileOutputStream outputStream = openFileOutput("login.txt", Context.MODE_PRIVATE);
                                    //数据录入-刷新
                                    outputStream.write("".getBytes());
                                    outputStream.flush();
                                    //页面跳用户主页
                                    goToUserHome();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create();
                alertDialog2.show();
            }
        });
    }

    //判断登录
    private Boolean loginState() {
        Intent intent = getIntent();
        String loginState = intent.getStringExtra("loginState");
        return loginState != null && loginState.equals("true");
    }


    //个人信息按钮
    private void userInformation() {
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.g_r_x_x);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证
                if (!loginState()) {
                    goToUserHome();
                } else {
                    //实现具体逻辑
                    Log.e("实现个人信息逻辑", "ture");
                }
            }
        });
    }

    //账号信息
    private void accountInformation() {
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.z_h_x_x);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证
                if (!loginState()) {
                    goToUserHome();
                } else {
                    //实现具体逻辑
                    Log.e("实现账号信息逻辑", "ture");
                }
            }
        });
    }

    //社区活动
    private void communityActivity() {
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.s_q_h_d);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证
                if (!loginState()) {
                    goToUserHome();
                } else {
                    //实现具体逻辑
                    Log.e("实现社区活动逻辑", "ture");
                }
            }
        });
    }

    //通用
    private void general() {
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.t_y);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通用页面跳转设置
                Intent intent = new Intent(UserSetting.this, General.class);
                //跳转
                startActivity(intent);
            }
        });
    }

    //新消息通知
//    private void alerts(){
//        //获取点击事件
//        RelativeLayout relativeLayout = findViewById(R.id.x_x_x_t_z);
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Notice notice = new Notice();
////                notice.sendSubscribeMsg(view);
//            }
//        });
//    }

    //跳转用户页面
    private void goToUserHome() {
        Intent intent = new Intent(UserSetting.this, NotLoginUser.class);
        //跳转
        startActivity(intent);
    }
}