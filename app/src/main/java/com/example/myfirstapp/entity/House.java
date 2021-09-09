package com.example.myfirstapp.entity;

import java.io.Serializable;

/**
 * 房屋信息实体类
 */
public class House implements Serializable {
    //房屋id
    private String h_id;
    //房屋地址
    private String h_address;
    //房屋价格
    private String h_price;
    //房屋面积
    private String h_space;
    //房屋类型（特点）
    private String h_type;
    //房屋展示图片
    private String h_image;

    @Override
    public String toString() {
        return "House{" +
                "h_id='" + h_id + '\'' +
                ", h_address='" + h_address + '\'' +
                ", h_price='" + h_price + '\'' +
                ", h_space='" + h_space + '\'' +
                ", h_type='" + h_type + '\'' +
                ", h_image='" + h_image + '\'' +
                '}';
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getH_address() {
        return h_address;
    }

    public void setH_address(String h_address) {
        this.h_address = h_address;
    }

    public String getH_price() {
        return h_price;
    }

    public void setH_price(String h_price) {
        this.h_price = h_price;
    }

    public String getH_space() {
        return h_space;
    }

    public void setH_space(String h_space) {
        this.h_space = h_space;
    }

    public String getH_type() {
        return h_type;
    }

    public void setH_type(String h_type) {
        this.h_type = h_type;
    }

    public String getH_image() {
        return h_image;
    }

    public void setH_image(String h_image) {
        this.h_image = h_image;
    }
}
