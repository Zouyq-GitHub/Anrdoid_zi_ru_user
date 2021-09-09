package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfirstapp.entity.User;
import com.example.myfirstapp.ui.dashboard.DashboardFragment;
import com.example.myfirstapp.ui.home.HomeFragment;
import com.example.myfirstapp.util.UpdateImg;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;


public class NotLoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login_user);
//        userStorage();
//        gotoLoginPage();
//        sheZhiIcon();
//        Fragment fragment = getFragmentManager().findFragmentById(R.id.fr)
        //fra
//        BottomNavigationView btmNavView;
//        btmNavView = findViewById(R.id.nav_view);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment());
//        }
//        btmNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        fragment = new HomeFragment();
//                        break;
//                    case R.id.navigation_dashboard:
//                        fragment = new DashboardFragment();
//                        break;
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
//                return true;
//            }
//        });
    }

    //用户消息通知
    private void userStorage() {
        ImageView imageView = findViewById(R.id.xiao_xi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用方法判断用户登录状态 登录跳消息 未登录跳登录
                boolean loginState = loginState();
                Intent intent;
                if (loginState) {
                    intent = new Intent(NotLoginUser.this, test_interaction.class);
                } else {
                    intent = new Intent(NotLoginUser.this, ZiRuLogin.class);
                }
                startActivity(intent);
            }
        });
    }

    //用户设置效果
    private void sheZhiIcon() {
        //获取图标id
        ImageView imageView = findViewById(R.id.she_zhi_icon);
        //点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置的点击事件 点击跳转设置的页面 但是要判断是否登录
                //判断登录 如果登录状态就传参 不然就不用传
                Intent intent = new Intent(NotLoginUser.this, UserSetting.class);
                if (loginState()) {
                    intent.putExtra("loginState", "true");
                }
                //跳转
                startActivity(intent);
            }
        });
    }

    //用户跳转登录
    private void gotoLoginPage() {
        //点击登录-注册
        //如果已经登录该功能不实现
        if (!loginState()) {
            TextView textView = findViewById(R.id.userStorage);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(NotLoginUser.this, ZiRuLogin.class);
                    //跳转
                    startActivity(intent);
                }
            });
        }
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
            //进行登录验证 文本和传入的用户id
            Intent intent = getIntent();//声明一个对象，并获得跳转过来的Intent对象
            //从intent对象中获得bundle对象
            Bundle bundle = intent.getExtras();
            //从bundle对象中提取数据 空指针异常
            String user_id;
            if (bundle == null) {
                return false;
            } else {
                user_id = bundle.getString("u_id");
            }
            //将json数据进行解析
            JSONObject resp = new JSONObject(sb.toString());
            String u_id = resp.getString("u_id");
            //登录判断 文档id和用户实体类id
            Log.e("文档：", u_id);
            return user_id.equals(u_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //初次到页面监听用户是否登录
    @Override
    protected void onStart() {
        super.onStart();
        Boolean loginState = loginState();
        Log.e("判断结果:", String.valueOf(loginState));
        //实例化用户对象
        User user = new User();
        if (loginState) {
            //登录的情况下 改变信息  获取id
            TextView textView = findViewById(R.id.userStorage);
            TextView textView1 = findViewById(R.id.userNotStorage);
            ImageView imageView = findViewById(R.id.user_Img);
            //获取用户名
            Intent intent = getIntent();
            //从intent对象中获得bundle对象
            Bundle bundle = intent.getExtras();
            //从bundle对象中提取数据
            String name = bundle.getString("u_name");
            String img = bundle.getString("u_image");
            String vip = bundle.getString("u_vip");
            //修改用户名
            textView.setText(name);
            //改VIP
            String vips = "VIP等级" + vip;
            textView1.setText(vips);
            //改头像
            UpdateImg updateImg = new UpdateImg();
            //赋值
            updateImg.imageView = imageView;
            //实现方法传入图片地址
            updateImg.bySrcUpdateImg(img);
            //自如客时光计划
            TextView textView2 = findViewById(R.id.textClickLogin);
            TextView textView3 = findViewById(R.id.lookYourVip);
            //占位置但不显示
            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }
    }
}