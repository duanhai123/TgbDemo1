package com.geebit.app1.bean;

/**
 * Created by DEll on 2016-12-29.
 */
public class LoginUser {


    /**
     * rtcode : 1
     * data : {"uid":2,"create_time":"2017-01-10 14:59:43","tel":"13043681212","status":1}
     */

    private int rtcode;
    private DataBean data;

    public int getRtcode() {
        return rtcode;
    }

    public void setRtcode(int rtcode) {
        this.rtcode = rtcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 2
         * create_time : 2017-01-10 14:59:43
         * tel : 13043681212
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
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "rtcode=" + rtcode +
                ", data=" + data +
                '}';
    }
}
