package com.geebit.app1.bean;/* data: 2017-01-12
 * author: 段海鹏
 * ui: 
 */

import java.util.List;

public class HoldingDetail1Bean {

    /**
     * rtcode : 1
     * data : [{"deal_date":"2017-01-12 11:29:22","org_amount":800.23,"asset_type":3,"so_nbr":98,"deal_amount":0.304,"trans_type":1},{"deal_date":"2017-01-11 11:32:50","org_amount":800.562,"asset_type":4,"so_nbr":100,"deal_amount":0.534,"trans_type":1},{"deal_date":"2017-01-10 17:05:46","org_amount":43200,"asset_type":1,"so_nbr":13,"deal_amount":800,"trans_type":1},{"deal_date":"2017-01-10 16:58:50","org_amount":44000,"asset_type":1,"so_nbr":12,"deal_amount":800,"trans_type":1},{"deal_date":"2017-01-10 16:41:59","org_amount":45000,"asset_type":1,"so_nbr":11,"deal_amount":1000,"trans_type":1},{"deal_date":"2017-01-10 16:39:16","org_amount":47000,"asset_type":1,"so_nbr":10,"deal_amount":2000,"trans_type":1},{"deal_date":"2017-01-10 14:30:01","org_amount":46000,"asset_type":1,"so_nbr":6,"deal_amount":-1000,"trans_type":2},{"deal_date":"2017-01-10 14:28:49","org_amount":48000,"asset_type":1,"so_nbr":5,"deal_amount":2000,"trans_type":1},{"deal_date":"2017-01-10 11:33:19","org_amount":800.342,"asset_type":4,"so_nbr":101,"deal_amount":0.316,"trans_type":1},{"deal_date":"2017-01-10 11:30:55","org_amount":800.35,"asset_type":3,"so_nbr":99,"deal_amount":0.134,"trans_type":1}]
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
         * deal_date : 2017-01-12 11:29:22
         * org_amount : 800.23
         * asset_type : 3
         * so_nbr : 98
         * deal_amount : 0.304
         * trans_type : 1
         */

        private String deal_date;
        private double org_amount;
        private int asset_type;
        private int so_nbr;
        private double deal_amount;
        private int trans_type;

        public String getDeal_date() {
            return deal_date;
        }

        public void setDeal_date(String deal_date) {
            this.deal_date = deal_date;
        }

        public double getOrg_amount() {
            return org_amount;
        }

        public void setOrg_amount(double org_amount) {
            this.org_amount = org_amount;
        }

        public int getAsset_type() {
            return asset_type;
        }

        public void setAsset_type(int asset_type) {
            this.asset_type = asset_type;
        }

        public int getSo_nbr() {
            return so_nbr;
        }

        public void setSo_nbr(int so_nbr) {
            this.so_nbr = so_nbr;
        }

        public double getDeal_amount() {
            return deal_amount;
        }

        public void setDeal_amount(double deal_amount) {
            this.deal_amount = deal_amount;
        }

        public int getTrans_type() {
            return trans_type;
        }

        public void setTrans_type(int trans_type) {
            this.trans_type = trans_type;
        }
    }

    @Override
    public String toString() {
        return "HoldingDetail1Bean{" +
                "rtcode=" + rtcode +
                ", data=" + data +
                '}';
    }
}
