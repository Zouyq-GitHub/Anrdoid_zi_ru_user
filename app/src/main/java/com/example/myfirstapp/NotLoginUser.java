package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
                Log.e("是否点击进入","true");
                String filename = "myfile";
                String fileContents = "Hello world!";
//                try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
//                    fos.write(fileContents.toByteArray());
//                }

            }
        });
    }

////    public static String rootXMLPath = Environment.getExternalStorageDirectory() + "/testTXT";
//        public static String rootXMLPath = "/storage/emulated/0";
//    /**
//     * 保存内容到TXT文件中
//     *
//     * @param fileName
//     * @param content
//     * @return
//     */
//    public static boolean writeToXML(String fileName, String content) {
//        Log.e("path:",rootXMLPath);
//        FileOutputStream fileOutputStream;
//        BufferedWriter bufferedWriter;
//        createDirectory(rootXMLPath);
//        File file = new File(rootXMLPath + "/" + fileName + ".txt");
//        try {
//            file.createNewFile();
//            fileOutputStream = new FileOutputStream(file);
//            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//            bufferedWriter.write(content);
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 读取XML内容
//     *
//     * @param filePath
//     * @return
//     */
//    public static String readFromXML(String filePath) {
//        FileInputStream fileInputStream;
//        BufferedReader bufferedReader;
//        StringBuilder stringBuilder = new StringBuilder();
//        File file = new File(filePath);
//        if (file.exists()) {
//            try {
//                fileInputStream = new FileInputStream(file);
//                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                bufferedReader.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        return stringBuilder.toString();
//    }
//
//
//
//    /**
//     * 创建文件夹
//     *
//     * @param fileDirectory
//     */
//    public static void createDirectory(String fileDirectory) {
//        File file = new File(fileDirectory);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//    }
}