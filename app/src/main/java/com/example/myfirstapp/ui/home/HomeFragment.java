package com.example.myfirstapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.myfirstapp.Index;
import com.example.myfirstapp.NotLoginUser;
import com.example.myfirstapp.R;
import com.example.myfirstapp.UserSetting;
import com.example.myfirstapp.ZiRuLogin;
import com.example.myfirstapp.adapter.IndexDataAdapter;
import com.example.myfirstapp.databinding.FragmentHomeBinding;
import com.example.myfirstapp.entity.House;
import com.example.myfirstapp.entity.LocalData;
import com.example.myfirstapp.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //实例化全局变量类 LocalData
        if (!LocalData.isLocal_data()) {
            //需要重新请求
            getData(v);
            LocalData localData = new LocalData();
            localData.setLocal_data(true);
        } else {
            //访问本地存储 不用重新请求
            System.out.println("访问本地存储");
            get_local_data(v);
        }
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //主页面请求用户数据 实现数据查询便利
    private void getData(View v) {
        FormBody.Builder params = new FormBody.Builder();
        //post参数
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.85.173:8080/api/getData")
                .post(params.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        //JSON转数组
                        try {
                            //success
                            String res = response.body().string();
                            //将数据写入  local_data.txt
                            System.out.println("数据写入是否成功  " + set_local_data(res));
                            //数据转换
                            JSONArray jsonArray = null;
                            jsonArray = new JSONArray(res);
                            //遍历方法
                            recyclerView_view(v, jsonArray);
//                            //数据转换
//                            JSONArray jsonArray = new JSONArray(res);
//                            //数据遍历
//                            RecyclerView recyclerView = v.findViewById(R.id.recycler_index_data);
//                            List<House> dataList = new ArrayList<>();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                House house = new House();
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                house.setH_id(jsonObject.getString("h_id"));
//                                house.setH_address(jsonObject.getString("h_address"));
//                                house.setH_space(jsonObject.getString("h_space"));
//                                house.setH_type(jsonObject.getString("h_type"));
//                                house.setH_price("￥" + jsonObject.getString("h_price") + "/月");
//                                house.setH_image(jsonObject.getString("h_image"));
//                                dataList.add(house);
//                            }
//                            //---
//                            IndexDataAdapter indexDataAdapter = new IndexDataAdapter(getActivity(), dataList);
//                            recyclerView.setAdapter(indexDataAdapter);
//                            //布局  瀑布流                                            new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.HORIZONTAL);
//                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//                            recyclerView.setLayoutManager(staggeredGridLayoutManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                //Error
                System.out.println("Error");
            }
        });
    }

    //写入本地缓存
    private boolean set_local_data(String data) {
        //数据录入login.txt   MODE_PRIVATE:数据重置-私有访问
        FileOutputStream outputStream = null;
        try {
            outputStream = getActivity().openFileOutput("local_data.txt", Context.MODE_PRIVATE);
            //数据录入-刷新
            outputStream.write(data.getBytes());
            outputStream.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //读取本地缓存
    private Boolean get_local_data(View v) {
        //读取数据
        try {
            //用StringBuilder来接收数据，而不是用String+=的方法。
            StringBuilder sb = new StringBuilder();
            //每次读取1024个byte的数据
            byte[] bytes = new byte[1024];
            FileInputStream inputStream = getActivity().openFileInput("local_data.txt");
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            //将json数据进行解析  resp是JSON数组
//            JSONArray resp = new JSONObject(sb.toString());
            JSONArray resp = new JSONArray(sb.toString());
            System.out.println("resp" + resp);
            //数据遍历
            recyclerView_view(v, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //数据遍历封装
    private void recyclerView_view(View v, JSONArray jsonArray) {
        try {
            //数据遍历
            RecyclerView recyclerView = v.findViewById(R.id.recycler_index_data);
            List<House> dataList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                House house = new House();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                house.setH_id(jsonObject.getString("h_id"));
                house.setH_address(jsonObject.getString("h_address"));
                house.setH_space(jsonObject.getString("h_space"));
                house.setH_type(jsonObject.getString("h_type"));
                house.setH_price("￥" + jsonObject.getString("h_price") + "/月");
                house.setH_image(jsonObject.getString("h_image"));
                dataList.add(house);
            }
            //---
            IndexDataAdapter indexDataAdapter = new IndexDataAdapter(getActivity(), dataList);
            recyclerView.setAdapter(indexDataAdapter);
            //布局  瀑布流                                            new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.HORIZONTAL);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //数据类型查看
    private String getType(Object a) {
        return a.getClass().toString();
    }
}