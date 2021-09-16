package com.example.myfirstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfirstapp.ui.dashboard.DashboardFragment;

import java.io.FileOutputStream;

import static android.provider.Settings.EXTRA_APP_PACKAGE;

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
        alerts();
        aboutUs();
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
                                    //页面跳用户页
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
        //获取用户id
        Intent intent1 = getIntent();
        String u_id = intent1.getStringExtra("u_id");
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.g_r_x_x);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证
                if (!loginState()) {
                    goToUserHome();
                } else {
                    //通用页面跳转设置
                    Intent intent = new Intent(UserSetting.this, UserData.class);
                    //传值
                    intent.putExtra("u_id", u_id);
                    //跳转
                    startActivity(intent);
                }
            }
        });
    }

    //账号信息
    private void accountInformation() {
        //获取用户id
        Intent intent1 = getIntent();
        String u_id = intent1.getStringExtra("u_id");
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.z_h_x_x);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //验证
                if (!loginState()) {
                    goToUserHome();
                } else {
                    //通用页面跳转设置
                    Intent intent = new Intent(UserSetting.this, AccountInformationManagement.class);
                    //传值
                    intent.putExtra("u_id", u_id);
                    //跳转
                    startActivity(intent);
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
                    //通用页面跳转设置
                    Intent intent = new Intent(UserSetting.this, CommunityActivitySetting.class);
                    //跳转
                    startActivity(intent);
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

    //关于我们
    private void aboutUs() {
        //点击事件
        RelativeLayout relativeLayout = findViewById(R.id.g_y_w_m);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通用页面跳转设置
                Intent intent = new Intent(UserSetting.this, aBoutUs.class);
                //跳转
                startActivity(intent);
            }
        });
    }

    //新消息通知
    private void alerts() {
        //获取点击事件
        RelativeLayout relativeLayout = findViewById(R.id.x_x_x_t_z);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                    //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
                    intent.putExtra(EXTRA_APP_PACKAGE, getPackageName());
//                    intent.putExtra(EXTRA_CHANNEL_ID, getApplicationInfo().uid);

                    //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
                    intent.putExtra("app_package", getPackageName());
                    intent.putExtra("app_uid", getApplicationInfo().uid);

                    // 小米6 -MIUI9.6-8.0.0系统，是个特例，通知设置界面只能控制"允许使用通知圆点"——然而这个玩意并没有卵用，我想对雷布斯说：I'm not ok!!!
                    //  if ("MI 6".equals(Build.MODEL)) {
                    //      intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    //      Uri uri = Uri.fromParts("package", getPackageName(), null);
                    //      intent.setData(uri);
                    //      // intent.setAction("com.android.settings/.SubSettings");
                    //  }
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
                    Intent intent = new Intent();
                    //下面这种方案是直接跳转到当前应用的设置界面。
                    //https://blog.csdn.net/ysy950803/article/details/71910806
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }
        });

    }

    //获取通知是否开启的权限
//    private fun initClickListener() {
//        tv_msg.setOnClickListener {
//            val intent: Intent = Intent()
//            try {
//                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
//
//                //8.0及以后版本使用这两个extra.  >=API 26
//                intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
//                intent.putExtra(Settings.EXTRA_CHANNEL_ID, applicationInfo.uid)
//
//                //5.0-7.1 使用这两个extra.  <= API 25, >=API 21
//                intent.putExtra("app_package", packageName)
//                intent.putExtra("app_uid", applicationInfo.uid)
//
//                startActivity(intent)
//            } catch (e: Exception) {
//                e.printStackTrace()
//
//                //其他低版本或者异常情况，走该节点。进入APP设置界面
//                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                intent.putExtra("package", packageName)
//
//                //val uri = Uri.fromParts("package", packageName, null)
//                //intent.data = uri
//                startActivity(intent)
//            }
//        }
//    }

    //跳转用户页面
    private void goToUserHome() {
        Intent intent = new Intent(UserSetting.this, Index.class);
        intent.putExtra("id", 2);
        setResult(3, intent);
        finish();
//        Fragment fragment = new DashboardFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}