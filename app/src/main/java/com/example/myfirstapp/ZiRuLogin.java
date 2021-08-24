package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.myfirstapp.MainActivity.EXTRA_MESSAGE;

public class ZiRuLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziru_login);
        fontColor();
        userSwitch();
    }

    //跳转登录的逻辑
    public void goToLogin(View view) {
        //发送请求 通过手机号查询用户信息
        FormBody.Builder params = new FormBody.Builder();
        //post参数
        params.add("phoneNumber", "18990027415");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.85.173:8080/api/loginState")
                .post(params.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                //success
                String res = response.body().string();
                Log.e("dada:", res);
                //数据录入login.txt   MODE_PRIVATE:数据重置-私有访问
                FileOutputStream outputStream = openFileOutput("login.txt", Context.MODE_PRIVATE);
                //数据录入-刷新
                outputStream.write(res.getBytes());
                outputStream.flush();
                Log.e("数据录入", "true");
                //数据存入实体类
                User user = new User();
                //test

//                Gson gson;
//                GsonBuilder builder;
//
//                //这两句代码必须的，为的是初始化出来gson这个对象，才能拿来用
//                builder = new GsonBuilder();
//                gson = builder.create();
//
//                user = gson.fromJson(response.body().string(), User.class);
//                Log.e("test", user.getU_name());


                //---
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                //Error
                System.out.println("Error");
            }
        });
        //页面跳转
        Intent intent = new Intent(this, NotLoginUser.class);
        startActivity(intent);
    }

    //字体颜色的设置
    private void fontColor() {
        final String Font = "自如网隐私政策并使用本机号码登录";
        String TEXT = "登录即同意中国电信认证服务条款和自如用户服务协议及" + Font;

        TextView textView = findViewById(R.id.xieYi);
        //SpannableStringBuilder用法  viewText改变字体颜色
        SpannableStringBuilder builder = new SpannableStringBuilder(TEXT);
        //单独设置字体颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FE794E"));
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#FE794E"));
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.parseColor("#FE794E"));
        builder.setSpan(colorSpan, 5, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(colorSpan1, 16, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(colorSpan2, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //不设置不生效
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(builder);
    }

    //切换账号
    public void userSwitch() {
        TextView textView = findViewById(R.id.userSwitch);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZiRuLogin.this, test_interaction.class);
                startActivity(intent);
            }
        });
    }
}