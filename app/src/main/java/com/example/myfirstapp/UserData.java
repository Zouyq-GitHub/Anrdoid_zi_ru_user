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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfirstapp.entity.User;
import com.example.myfirstapp.util.BitmapUtils;
import com.example.myfirstapp.util.CameraUtils;
import com.example.myfirstapp.util.SPUtils;
import com.example.myfirstapp.util.UpdateImg;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserData extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private User user = new User();

    //----头像
    //权限请求
    private RxPermissions rxPermissions;

    //是否拥有权限
    private boolean hasPermissions = false;

    //底部弹窗
    private BottomSheetDialog bottomSheetDialog;
    //弹窗视图
    private View bottomView;

    //存储拍完照后的图片
    private File outputImagePath;
    //启动相机标识
    public static final int TAKE_PHOTO = 6;
    //启动相册标识
    public static final int SELECT_PHOTO = 5;

    //图片控件
    private ShapeableImageView ivHead;
    //Base64
    private String base64Pic;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        ivHead = findViewById(R.id.iv_head);
        //检查版本
        checkVersion();
        //取出缓存
        String imageUrl = SPUtils.getString("imageUrl", null, this);
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).apply(requestOptions).into(ivHead);
        }
        newUserName();
        sendUserIdByUserData();
//        newUserPhoto();
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
//        imageView = findViewById(R.id.user_data_img_id);
        textView.setText("自如客  " + user.getU_name());
//        //图片修改
//        UpdateImg updateImg = new UpdateImg();
//        //赋值
//        updateImg.imageView = imageView;
//        //实现方法传入图片地址
//        updateImg.bySrcUpdateImg(user.getU_image());
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

    //更换头像方法

    /**
     * 检查版本
     */
    private void checkVersion() {
        //Android6.0及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果你是在Fragment中，则把this换成getActivity()
            rxPermissions = new RxPermissions(this);
            //权限请求
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {//申请成功
                            showMsg("已获取权限");
                            hasPermissions = true;
                        } else {//申请失败
                            showMsg("权限未开启");
                            hasPermissions = false;
                        }
                    });
        } else {
            //Android6.0以下
            showMsg("无需请求动态权限");
        }
    }

    /**
     * 更换头像 点击事件
     *
     * @param view
     */
    public void changeAvatar(View view) {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomView = getLayoutInflater().inflate(R.layout.dialog_bottom, null);
        bottomSheetDialog.setContentView(bottomView);
        bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
        TextView tvTakePictures = bottomView.findViewById(R.id.tv_take_pictures);
        TextView tvOpenAlbum = bottomView.findViewById(R.id.tv_open_album);
        TextView tvCancel = bottomView.findViewById(R.id.tv_cancel);

        //拍照
        tvTakePictures.setOnClickListener(v -> {
            takePhoto();
            showMsg("拍照");
            bottomSheetDialog.cancel();
        });
        //打开相册
        tvOpenAlbum.setOnClickListener(v -> {
            openAlbum();
            showMsg("打开相册");
            bottomSheetDialog.cancel();
        });
        //取消
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.cancel();
        });
        //底部弹窗显示
        bottomSheetDialog.show();
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        outputImagePath = new File(getExternalCacheDir(),
                filename + ".jpg");
        Intent takePhotoIntent = CameraUtils.getTakePhotoIntent(this, outputImagePath);
        // 开启一个带有返回值的Activity，请求码为TAKE_PHOTO
        startActivityForResult(takePhotoIntent, TAKE_PHOTO);
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
    }

    /**
     * 返回到Activity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //拍照后返回
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //显示图片
                    displayImage(outputImagePath.getAbsolutePath());
                }
                break;
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImageOnKitKatPath(data, this);
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, this);
                    }
                    //显示图片
                    displayImage(imagePath);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 通过图片路径显示图片
     */
    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {

            //放入缓存
            SPUtils.putString("imageUrl", imagePath, this);

            //显示图片
            Glide.with(this).load(imagePath).apply(requestOptions).into(ivHead);

            //压缩图片
            orc_bitmap = CameraUtils.compression(BitmapFactory.decodeFile(imagePath));
            //转Base64
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        } else {
            showMsg("图片获取失败");
        }
    }


    /**
     * Toast提示
     *
     * @param msg
     */
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}