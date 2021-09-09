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
import com.example.myfirstapp.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //主页面请求数据
        getData(v);
//        RecyclerView recyclerView = v.findViewById(R.id.recycler_index_data);
//        List<House> dataList = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            House house = new House();
//            house.setH_price("9876");
//            house.setH_address("青羊区");
//            house.setH_space("3室2厅130㎡");
//            house.setH_type("密码锁 锁锁锁锁");
//            System.out.println("我正在遍历第" + i + "个");
//            dataList.add(house);
//        }
//        IndexDataAdapter indexDataAdapter = new IndexDataAdapter(getActivity(), dataList);
//        recyclerView.setAdapter(indexDataAdapter);
//        //布局  瀑布流                                            new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.HORIZONTAL);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //布局  线性布局
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyclerView.setLayoutManager(linearLayoutManager);
        //网格布局
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
//        recyclerView.setLayoutManager(gridLayoutManager);
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
                //success
                String res = response.body().string();
                //JSON转数组
                try {
                    JSONArray jsonArray = new JSONArray(res);
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

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                //Error
                System.out.println("Error");
            }

            //数据类型查看
            private String getType(Object a) {
                return a.getClass().toString();
            }
        });
    }
}