package com.example.myfirstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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
//        viewHolder.h_image.set
//        viewHolder.h_image.set
//        viewHolder.h_image.setImageResource(dataList.get(position).getH_image());
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
