package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NotLoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login_user);
        userStorage();
    }

    //用户本地存储方法
    private void userStorage(){
        TextView textView = findViewById(R.id.userStorage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String FILENAME = "hello_file.txt";
//                String string = "hello world!";
//
//                FileOutputStream fos = null;
//                try {
//                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//                    fos.write(string.getBytes());
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Log.e("test","demo123");
            }
        });

    }
}