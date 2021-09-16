package com.example.myfirstapp.ui.dashboard;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myfirstapp.NotLoginUser;
import com.example.myfirstapp.R;
import com.example.myfirstapp.UserSetting;
import com.example.myfirstapp.ZiRuLogin;
import com.example.myfirstapp.entity.User;
import com.example.myfirstapp.test_interaction;
import com.example.myfirstapp.util.UpdateImg;

import org.json.JSONObject;

import java.io.FileInputStream;

public class DashboardFragment extends Fragment {

    private View view;
    private Button btn;
    private User user = new User();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sheZhiIcon();
        communityLife();
        gotoLoginPage();
        userStorage();
    }

    //用户消息通知
    private void userStorage() {
        ImageView imageView = getActivity().findViewById(R.id.xiao_xi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用方法判断用户登录状态 登录跳消息 未登录跳登录
                Intent intent = getActivity().getIntent();
                if (loginState()) {
                    intent = new Intent(getActivity(), test_interaction.class);
                } else {
                    intent = new Intent(getActivity(), ZiRuLogin.class);
                }
                startActivityForResult(intent, 2);
            }
        });
    }

    //用户设置效果
    private void sheZhiIcon() {
        ImageView imageView = getActivity().findViewById(R.id.user_head_Img);
        imageView = view.findViewById(R.id.she_zhi_icon_fra);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserSetting.class); //跳转
                //判定登录状态
                if (loginState()) {
                    //传入用户ID
                    String u_id = user.getU_id();
                    intent.putExtra("u_id", u_id);
                    intent.putExtra("loginState", "true");
                }
                startActivityForResult(intent, 4);
            }
        });
    }

    //社区生活
    private void communityLife() {
        btn = view.findViewById(R.id.s_q_s_h_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "社区生活", 0).show();
            }
        });
    }

    //验证是否登录方法  判断用户登录状态
    public boolean loginState() {
        //读取数据:
        try {
            //用StringBuilder来接收数据，而不是用String+=的方法。
            StringBuilder sb = new StringBuilder();
            //每次读取1024个byte的数据
            byte[] bytes = new byte[1024];
            FileInputStream inputStream = getActivity().openFileInput("login.txt");
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            //将json数据进行解析
            if (!sb.toString().equals("")) {
                JSONObject resp = new JSONObject(sb.toString());
                String u_id = resp.getString("u_id");
                String u_image = resp.getString("u_image");
                String u_name = resp.getString("u_name");
                String u_phone = resp.getString("u_phone");
                String u_vip = resp.getString("u_vip");
                //登录判断 文档id和用户实体类id
                if (u_id.equals("")) {
                    return false;
                } else {
                    //将数据存到实体类
                    user.setU_id(u_id);
                    user.setU_image(u_image);
                    user.setU_name(u_name);
                    user.setU_phone(u_phone);
                    user.setU_vip(u_vip);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //登录返回到页面监听用户是否登录
    @Override
    public void onStart() {
        super.onStart();
        if (loginState()) {
            //登录的情况下 改变信息  获取id
            TextView textView = getActivity().findViewById(R.id.userStorage);
            TextView textView1 = getActivity().findViewById(R.id.userNotStorage);
            ImageView imageView = getActivity().findViewById(R.id.user_head_Img);
            //获取用户名
            Intent intent = getActivity().getIntent();
            //从bundle对象中提取数据
            String name = user.getU_name();
            String img = user.getU_image();
            String vip = user.getU_vip();
            //修改用户名
            textView.setText(name);
            //改VIP
            String vips = "VIP等级" + vip;
            textView1.setText(vips);
            //图片
            UpdateImg updateImg = new UpdateImg();
            //赋值
            updateImg.imageView = imageView;
            //实现方法传入图片地址
            updateImg.bySrcUpdateImg(img);
            //自如客时光计划
            TextView textView2 = getActivity().findViewById(R.id.textClickLogin);
            TextView textView3 = getActivity().findViewById(R.id.lookYourVip);
            //占位置但不显示
            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }
    }

    //用户跳转登录
    private void gotoLoginPage() {
        //点击登录-注册
        //如果已经登录该功能不实现
        if (!loginState()) {
            TextView textView = getActivity().findViewById(R.id.userStorage);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ZiRuLogin.class);
                    //跳转
                    startActivityForResult(intent, 3);
                }
            });
        }
    }

    //finish回调
    //2 -- 修改名称
    //3 -- 登录页面回调
    //4 -- 设置

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && data!=null) {
            Bundle bundle = data.getExtras();
            System.out.println(bundle.getString("u_image"));

            //登录的情况下 改变信息  获取id
            TextView textView = getActivity().findViewById(R.id.userStorage);
            TextView textView1 = getActivity().findViewById(R.id.userNotStorage);
            ImageView imageView = getActivity().findViewById(R.id.user_head_Img);
            //从bundle对象中提取数据
            String name = bundle.getString("u_name");
            String img = bundle.getString("u_image");
            String vip = bundle.getString("u_vip");
            //修改用户名
            textView.setText(name);
            //改VIP
            String vips = "VIP等级" + vip;
            textView1.setText(vips);
            //图片
            UpdateImg updateImg = new UpdateImg();
            //赋值
            updateImg.imageView = imageView;
            //实现方法传入图片地址
            updateImg.bySrcUpdateImg(img);
            //自如客时光计划
            TextView textView2 = getActivity().findViewById(R.id.textClickLogin);
            TextView textView3 = getActivity().findViewById(R.id.lookYourVip);
            //占位置但不显示
            textView2.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        } else {
            if (loginState()) {
                onStart();
            }
            {
                gotoLoginPage();
                //退出登录的情况
                //登录的情况下 改变信息  获取id
                TextView textView = getActivity().findViewById(R.id.userStorage);
                TextView textView1 = getActivity().findViewById(R.id.userNotStorage);
                ImageView imageView = getActivity().findViewById(R.id.user_head_Img);
                //修改用户名
                textView.setText("登录/注册");
                //改VIP
                textView1.setText("点击登录/注册");
                //图片
                imageView.setImageResource(R.drawable.ic_baseline_account_circle_24);
                //自如客时光计划
                TextView textView2 = getActivity().findViewById(R.id.textClickLogin);
                TextView textView3 = getActivity().findViewById(R.id.lookYourVip);
                //占位置但不显示
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
            }
        }
    }
}