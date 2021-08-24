package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class test_interaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_interaction);
        //btn点击事件处理
        //实例化btn按钮
        Button btn_get = findViewById(R.id.getButton);

        Button btn_post = findViewById(R.id.postButton);

        //点击触发点击事件
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("点击", "get调用");
            }
        });

        //点击触发post事件
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("点击", "post调用");
                // -------------------------------------------------------------------------------------------------


                //-------------------------------------------------------------------------------------------------
                //方法2    okhttp3.x  √  完美实现post带参数与不带参数  √
//                FormBody.Builder params=new FormBody.Builder();
//                //params.add("curr", "16");
//                OkHttpClient okHttpClient=new OkHttpClient();
//                Request request=new Request.Builder()
//                        .url("http://zouyq.top:8087/queryPhotoAlbumList")
//                        .post(params.build())
//                        .build();
//                Call call=okHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onResponse(Call arg0, Response response) throws IOException {
//                        //   响应成功  response.body().string() 获取字符串数据，当然还可以获取其他
//                         String res = response.body().string();
//                         Log.e("dada:",res);
//                    }
//
//                    @Override
//                    public void onFailure(Call arg0, IOException arg1) {
//                        //   响应失败
//                        Log.e("dada:","error");
//                    }
//                });
                //--------------------------------------------------------------
                // 方法3 测试  get请求  带参数 与不带参数 取决于.url的值
                FormBody.Builder params = new FormBody.Builder();
                //post参数
                params.add("phoneNumber", "18990027415");
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        //
                        .url("http://192.168.85.173:8080/api/loginState")
//                        .get()
                        //  post方法
                        .post(params.build())
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call arg0, Response response) throws IOException {
                        //   响应成功  response.body().string() 获取字符串数据，当然还可以获取其他
                        String res = response.body().string();
                        Log.e("dada:", res);
                    }

                    @Override
                    public void onFailure(Call arg0, IOException arg1) {
                        //   响应失败
                        System.out.println(arg0);
                        System.out.println(arg1);
                    }
                });
            }
        });
    }
}