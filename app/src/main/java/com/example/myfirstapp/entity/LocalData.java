package com.example.myfirstapp.entity;

//全局变量类
public class LocalData {

    static boolean local_data = false;

    public static boolean isLocal_data() {
        return local_data;
    }

    public void setLocal_data(boolean local_data) {
        this.local_data = local_data;
    }

}
