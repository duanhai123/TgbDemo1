package com.geebit.app1.bean;

/**
 * Created by DEll on 2016-12-28.
 */
public class User {

    public String tel ;
    public String password  ;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String tel, String password) {
        this.tel = tel;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}