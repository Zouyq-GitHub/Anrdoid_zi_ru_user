//package com.example.myfirstapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.myfirstapp.entity.User;
//import com.example.myfirstapp.util.UpdateImg;
//import com.google.gson.Gson;
//
//import java.io.IOException;
//
//public class UserData extends AppCompatActivity {
//
//    private ImageView imageView;
//    private TextView textView;
//    private User user = new User();
//    private Handler handler = null;
//    private String res = null;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_data);
//        sendUserIdByUserData();
//        newUserName();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    //发送用户id  获取用户数据信息
//    private void sendUserIdByUserData() {
//        //获取用户id
//        Intent intent = getIntent();
//        String u_id = intent.getStringExtra("u_id");
//        //查询用户信息
//        FormBody.Builder params = new FormBody.Builder();
//        //post参数
//        params.add("u_id", u_id);
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                //
//                .url("http://192.168.85.173:8080/api/byIdUserData")
//                .post(params.build())
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call arg0, Response response) throws IOException {
//                //线程处理
//                Looper.prepare();
//                res = response.body().string();
//                handler = new Handler();
//                new Thread() {
//                    public void run() {
//                        try {
//                            res = response.body().string();
//                            handler.post(runnableUi);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
////                Log.e("dada:", res);
//                //res的值处理
////                Gson gson = new Gson();
////                user = gson.fromJson(res, User.class);
////                textView = findViewById(R.id.user_id);
////                imageView = findViewById(R.id.user_data_img_id);
////                //用这个值 修改当前页面数据
////                textView.setText("自如客  " + user.getU_name());
////                //图片修改
////                UpdateImg updateImg = new UpdateImg();
////                //赋值
////                updateImg.imageView = imageView;
////                //实现方法传入图片地址
////                updateImg.bySrcUpdateImg(user.getU_image());
//                Looper.loop();
//            }
//
//            @Override
//            public void onFailure(Call arg0, IOException arg1) {
//                // 响应失败
//                System.out.println(arg0);
//                System.out.println(arg1);
//            }
//        });
//        //更新用户数据
////        updateUserData(res);
//    }
//
//    //用户数据更新
//    private void updateUserData(String res) {
//        System.out.println("res:" + res);
//        Gson gson = new Gson();
//        user = gson.fromJson(res, User.class);
//        textView = findViewById(R.id.user_id);
//        imageView = findViewById(R.id.user_data_img_id);
//        textView.setText("自如客  " + user.getU_name());
//        //图片修改
//        UpdateImg updateImg = new UpdateImg();
//        //赋值
//        updateImg.imageView = imageView;
//        //实现方法传入图片地址
//        updateImg.bySrcUpdateImg(user.getU_image());
//    }
//
//    //用户修改名称
//    private void newUserName() {
//        textView = findViewById(R.id.new_user_name);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //通用页面跳转设置
//                Intent intent = new Intent(UserData.this, User_new_name_activity.class);
//                //传值
//                intent.putExtra("u_username", user.getU_name());
//                intent.putExtra("u_id", user.getU_id());
//                //跳转
//                startActivity(intent);
//            }
//        });
//    }
//
//    // 构建Runnable对象，在runnable中更新界面
//    Runnable runnableUi = new Runnable() {
//        @Override
//        public void run() {
//            updateUserData(res);
//        }
//
//    };
//}

package com.example.myfirstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.entity.User;
import com.example.myfirstapp.util.UpdateImg;
import com.google.gson.Gson;

import java.io.IOException;

public class UserData extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        sendUserIdByUserData();
        newUserName();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //发送用户id  获取用户数据信息
    private void sendUserIdByUserData() {

        //获取用户id
        Intent intent = getIntent();
        String u_id = intent.getStringExtra("u_id");
        //查询用户信息
        FormBody.Builder params = new FormBody.Builder();
        //post参数
        params.add("u_id", u_id);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                //
                .url("http://192.168.85.173:8080/api/byIdUserData")
                .post(params.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Looper.prepare();//增加部分
                        String res = null;
                        try {
                            res = response.body().string();
                            updateUserData(res);
//                            Looper.loop();//增加部分
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // 响应失败
                System.out.println(arg0);
                System.out.println(arg1);
            }
        });

    }

    //用户数据更新
    private void updateUserData(String res) {
        System.out.println("res:" + res);
        Gson gson = new Gson();
        user = gson.fromJson(res, User.class);
        textView = findViewById(R.id.user_id);
        imageView = findViewById(R.id.user_data_img_id);
        textView.setText("自如客  " + user.getU_name());
        //图片修改
        UpdateImg updateImg = new UpdateImg();
        //赋值
        updateImg.imageView = imageView;
        //实现方法传入图片地址
        updateImg.bySrcUpdateImg(user.getU_image());
    }

    //用户修改名称
    private void newUserName() {
        textView = findViewById(R.id.new_user_name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通用页面跳转设置
                Intent intent = new Intent();
                intent.setClass(UserData.this, User_new_name_activity.class);
                //传值
                intent.putExtra("u_username", user.getU_name());
                intent.putExtra("u_id", user.getU_id());
                //跳转
                startActivityForResult(intent, 2);
            }
        });
    }

    // finish()回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sendUserIdByUserData();
    }
}