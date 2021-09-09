package com.example.myfirstapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.IndexDataAdapter;
import com.example.myfirstapp.databinding.FragmentNotificationsBinding;
import com.example.myfirstapp.entity.House;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_index_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<House> dataList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            House house = new House();
            house.setH_price("9876");
            house.setH_address("青羊区");
            house.setH_space("3室2厅130㎡");
            house.setH_type("密码锁 锁锁锁锁");
            dataList.add(house);
            System.out.println("我循环了几次" + i);
        }
        IndexDataAdapter indexDataAdapter = new IndexDataAdapter(getActivity(), dataList);
        recyclerView.setAdapter(indexDataAdapter);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}