package com.geebit.app1.bean;

/**
 * Created by DEll on 2016-12-30.
 */
public class RtcodeCommCode {


    /**
     * rtcode : 1
     * data : {"user_id":9,"recommend_code":"8k20lZ"}
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
         * user_id : 9
         * recommend_code : 8k20lZ
         */

        private int user_id;
        private String recommend_code;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getRecommend_code() {
            return recommend_code;
        }

        public void setRecommend_code(String recommend_code) {
            this.recommend_code = recommend_code;
        }
    }

    @Override
    public String toString() {
        return "RtcodeCommCode{" +
                "rtcode=" + rtcode +
                ", data=" + data +
                '}';
    }
}
