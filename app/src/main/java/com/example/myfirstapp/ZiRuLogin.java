package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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