package com.example.myfirstapp.entity;

//用户实体类
public class User {

    //用户id
    private String u_id;

    //用户名
    private String u_name;

    //用户头像
    private String u_image;

    //用户会员等级
    private String u_vip;

    //用户手机号
    private String u_phone;

    //用户密码
    private String u_password;

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_image() {
        return u_image;
    }

    public void setU_image(String u_image) {
        this.u_image = u_image;
    }

    public String getU_vip() {
        return u_vip;
    }

    public void setU_vip(String u_vip) {
        this.u_vip = u_vip;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    @Override
    public String toString() {
        return "user{" +
                "u_id='" + u_id + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_image='" + u_image + '\'' +
                ", u_vip='" + u_vip + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", u_password='" + u_password + '\'' +
                '}';
    }
}
