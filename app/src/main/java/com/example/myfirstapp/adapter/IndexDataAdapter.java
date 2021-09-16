package com.example.myfirstapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.House_detailed;
import com.example.myfirstapp.Index;
import com.example.myfirstapp.NotLoginUser;
import com.example.myfirstapp.R;
import com.example.myfirstapp.ZiRuLogin;
import com.example.myfirstapp.entity.House;
import com.example.myfirstapp.util.UpdateImg;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IndexDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<House> dataList;

    public IndexDataAdapter(Context context, List<House> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_notifications, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        House house = dataList.get(position);
        viewHolder.h_price.setText(house.getH_price());
        viewHolder.h_space.setText(house.getH_space());
        viewHolder.h_type.setText(house.getH_type());
        //改头像
        UpdateImg updateImg = new UpdateImg();
        //赋值
        updateImg.imageView = viewHolder.h_image;
        //实现方法传入图片地址
        updateImg.bySrcUpdateImg(house.getH_image());
        //点击事件监听
        ((ViewHolder) holder).h_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数据
                //System.out.println(dataList.get(position));
                //传值
                Intent intent = new Intent(mContext, House_detailed.class);
                House house1 = dataList.get(position);
                //传用户对象做登录验证及信息录入
                Bundle bundle = new Bundle();//声明一个Bundle对象，用来存放数据
                bundle.putString("u_id", house1.getH_id());//为bundle添加数据
                bundle.putString("u_image", house1.getH_image());
                bundle.putString("h_price", house1.getH_price());
                bundle.putString("h_space", house1.getH_space());
                bundle.putString("h_type", house1.getH_type());
                bundle.putString("h_address", house1.getH_address());
                intent.putExtras(bundle);//将这个bundle绑定在intent上
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView h_price;
        private ImageView h_image;
        private TextView h_space;
        private TextView h_type;


        public ViewHolder(@NonNull View view) {
            super(view);
            h_price = view.findViewById(R.id.h_price);
            h_image = view.findViewById(R.id.h_image);
            h_space = view.findViewById(R.id.h_space);
            h_type = view.findViewById(R.id.h_type);
        }
    }
}
