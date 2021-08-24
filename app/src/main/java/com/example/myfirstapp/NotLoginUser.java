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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class NotLoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_login_user);
        userStorage();
    }

    //用户本地存储方法
    private void userStorage() {
        TextView textView = findViewById(R.id.userStorage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("是否点击进入", "true");
//                String filename = "test1";
//                String fileContents = "Hello world!";
//                //文件写入
//                try {
//                    FileOutputStream fileOut = openFileOutput(filename, MODE_PRIVATE);
//                    Log.e("路径:", String.valueOf(fileOut));
//                    //实际上写入文件的是一个字节数组，我们用gettBytes()方法将其转换成目标格式
//                    fileOut.write(fileContents.getBytes(StandardCharsets.UTF_8));
//                    //记得写入完毕后要用close()结束
//                    fileOut.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                String filename = "myfile";
//                String fileContents = "Hello world!";
//
//                try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
//                    fos.write(fileContents.toByteArray());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //Context中的方法openFileOutput()，获取一个FileOutputStream对象
                try {
                    //用MODE_PRIVAT模式，创建一个hxy.txt文件
                    FileOutputStream outputStream = openFileOutput("hxy.txt",Context.MODE_APPEND);
                    //创建一个Usr对象(实现了Serializable接口，来让User对象可以通过流写入)
                    //下面的就没么好说的了，写入方法和冲刷方法
                    outputStream.write("Hello World".getBytes());
                    outputStream.flush();
                    outputStream.write("Hello World123".getBytes());
                    outputStream.flush();

                    //读取数据

                    //用StringBuilder来接收数据，而不是用String+=的方法。
                    StringBuilder sb = new StringBuilder();
                    //每次读取1024个byte的数据
                    byte[] bytes = new byte[1024];
                    FileInputStream inputStream = openFileInput("hxy.txt");
                    int len = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        sb.append(new String(bytes, 0, len));
                    }
                    Log.e("读取到的数据", sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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