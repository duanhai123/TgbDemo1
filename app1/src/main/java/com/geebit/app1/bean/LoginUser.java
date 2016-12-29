package com.geebit.app1.bean;

/**
 * Created by DEll on 2016-12-29.
 */
public class LoginUser {

    /**
     * uid : 20
     * create_time : 2016-12-29 09:42:37
     * tel : 13043680981
     * status : 1
     */

    private int uid;
    private String create_time;
    private String tel;
    private int status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "uid=" + uid +
                ", create_time='" + create_time + '\'' +
                ", tel='" + tel + '\'' +
                ", status=" + status +
                '}';
    }
}
