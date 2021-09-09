//package com.example.myfirstapp;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
//{
//    private List<Huoying> huoyingList;
//
//    public RecyclerAdapter(List<Huoying> huoyingList)
//    {
//        this.huoyingList = huoyingList;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(itemView);
//        return viewHolder;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position)
//    {
//        holder.name.setText(huoyingList.get(position).getName());
//        holder.number.setText(String.valueOf(huoyingList.get(position).getNumber()));
//        holder.trump.setText(huoyingList.get(position).getTrump());
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return huoyingList.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder
//    {
//        TextView name; //火影名字
//        TextView number; //火影的代号
//        TextView trump; //他的绝招
//
//        public ViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            this.name = itemView.findViewById(R.id.tv_name);
//            this.number = itemView.findViewById(R.id.tv_number);
//            this.trump = itemView.findViewById(R.id.tv_trump);
//        }
//
//    }
//}
