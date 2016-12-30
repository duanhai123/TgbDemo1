package com.geebit.app1.bean;

/**
 * Created by DEll on 2016-12-30.
 */
public class RegisterUser {

    /**
     * rtcode : 1
     * data : {"uid":29}
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
         * uid : 29
         */

        private int uid;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "rtcode=" + rtcode +
                ", data=" + data +
                '}';
    }
}
