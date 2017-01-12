package com.geebit.app1.bean;/* data: 2017-01-10
 * author: 段海鹏
 * ui: 产品类型
 */

import java.util.List;

public class Products {

    /**
     * rtcode : 1
     * data : [{"prod_type_id":1,"fulfillment_status":2,"risk_control":"RISK_CONTROL","batch_id":1,"prod_spec_id":1,"prod_code":"XGB1","prod_id":1,"prod_name":"XGB 一型","valid_date":"2017-01-10","interest_rate":0.0023,"currency":"RMB","create_date":"2017-01-10 13:05:10","status":1},{"prod_type_id":2,"fulfillment_status":2,"risk_control":"RISK_CONTROL BTC","batch_id":2,"prod_spec_id":2,"prod_code":"XGB2","prod_id":2,"prod_name":"XGB 二型","valid_date":"2017-01-10","interest_rate":0.0023,"currency":"BTC","create_date":"2017-01-10 13:21:54","status":1}]
     */

    private int rtcode;
    private List<DataBean> data;

    public int getRtcode() {
        return rtcode;
    }

    public void setRtcode(int rtcode) {
        this.rtcode = rtcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * prod_type_id : 1
         * fulfillment_status : 2
         * risk_control : RISK_CONTROL
         * batch_id : 1
         * prod_spec_id : 1
         * prod_code : XGB1
         * prod_id : 1
         * prod_name : XGB 一型
         * valid_date : 2017-01-10
         * interest_rate : 0.0023
         * currency : RMB
         * create_date : 2017-01-10 13:05:10
         * status : 1
         */

        private int prod_type_id;
        private int fulfillment_status;
        private String risk_control;
        private int batch_id;
        private int prod_spec_id;
        private String prod_code;
        private int prod_id;
        private String prod_name;
        private String valid_date;
        private double interest_rate;
        private String currency;
        private String create_date;
        private int status;

        public int getProd_type_id() {
            return prod_type_id;
        }

        public void setProd_type_id(int prod_type_id) {
            this.prod_type_id = prod_type_id;
        }

        public int getFulfillment_status() {
            return fulfillment_status;
        }

        public void setFulfillment_status(int fulfillment_status) {
            this.fulfillment_status = fulfillment_status;
        }

        public String getRisk_control() {
            return risk_control;
        }

        public void setRisk_control(String risk_control) {
            this.risk_control = risk_control;
        }

        public int getBatch_id() {
            return batch_id;
        }

        public void setBatch_id(int batch_id) {
            this.batch_id = batch_id;
        }

        public int getProd_spec_id() {
            return prod_spec_id;
        }

        public void setProd_spec_id(int prod_spec_id) {
            this.prod_spec_id = prod_spec_id;
        }

        public String getProd_code() {
            return prod_code;
        }

        public void setProd_code(String prod_code) {
            this.prod_code = prod_code;
        }

        public int getProd_id() {
            return prod_id;
        }

        public void setProd_id(int prod_id) {
            this.prod_id = prod_id;
        }

        public String getProd_name() {
            return prod_name;
        }

        public void setProd_name(String prod_name) {
            this.prod_name = prod_name;
        }

        public String getValid_date() {
            return valid_date;
        }

        public void setValid_date(String valid_date) {
            this.valid_date = valid_date;
        }

        public double getInterest_rate() {
            return interest_rate;
        }

        public void setInterest_rate(double interest_rate) {
            this.interest_rate = interest_rate;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
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
        return "Products{" +
                "rtcode=" + rtcode +
                ", data=" + data +
                '}';
    }
}
