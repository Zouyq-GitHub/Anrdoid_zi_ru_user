package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.entity.User;
import com.example.myfirstapp.util.UpdateImg;
import com.google.gson.Gson;

import java.io.IOException;

public class User_new_name_activity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_new_name);
        editText = findViewById(R.id.my_new_name);
        //获取用户昵称
        Intent intent = getIntent();
        String username = intent.getStringExtra("u_username");
        //显示当前用户昵称
        editText.setText(username);
        new_name_submit();
    }

    //提交新名称
    private void new_name_submit() {
        textView = findViewById(R.id.submit);
        editText = findViewById(R.id.my_new_name);

        //获取用户id
        Intent intent = getIntent();
        String u_id = intent.getStringExtra("u_id");

        //submit
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast ts = Toast.makeText(getBaseContext(), "修改完成", Toast.LENGTH_LONG);
                ts.show();
                //发送请求
                FormBody.Builder params = new FormBody.Builder();
                //post参数                        提交的名字和用户ID
                params.add("userName", editText.getText().toString());
                params.add("u_id", u_id);
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.85.173:8080/api/newUserName")
                        .post(params.build())
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call arg0, Response response) throws IOException {
                        //线程处理
                        Looper.prepare();
                        Looper.loop();
                    }

                    @Override
                    public void onFailure(Call arg0, IOException arg1) {
                        // 响应失败
                        System.out.println(arg0);
                        System.out.println(arg1);
                    }
                });
                //页面跳转回页面DATA
                Intent intent = new Intent(User_new_name_activity.this, UserData.class);
                //传值
                intent.putExtra("u_id", u_id);
                //跳转
                startActivity(intent);
            }
        });
    }


}