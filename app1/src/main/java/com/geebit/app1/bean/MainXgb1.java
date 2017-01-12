package com.geebit.app1.bean;/* data: 2017-01-11
 * author: 段海鹏
 * ui: 
 */

public class MainXgb1 {


    /**
     * rtcode : 1
     * data : {"bonusForYearPlan":9.84,"bonusRateForYearPlan":0.0123,"bonus_amount":0.02,"bonus_rate":0.0234,"hold_amount":800,"interest_amount":0,"interest_rate":0,"restRateDays":97,"sum_amount_year":0.5,"sum_bonus_rate_year":0.0502,"sum_bonus_year":0.04,"sum_rate_year":0.368,"total_amount":0.54,"total_rate":0.4182}
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
         * bonusForYearPlan : 9.84
         * bonusRateForYearPlan : 0.0123
         * bonus_amount : 0.02
         * bonus_rate : 0.0234
         * hold_amount : 800
         * interest_amount : 0
         * interest_rate : 0
         * restRateDays : 97
         * sum_amount_year : 0.5
         * sum_bonus_rate_year : 0.0502
         * sum_bonus_year : 0.04
         * sum_rate_year : 0.368
         * total_amount : 0.54
         * total_rate : 0.4182
         */

        private double bonusForYearPlan;
        private double bonusRateForYearPlan;
        private double bonus_amount;
        private double bonus_rate;
        private int hold_amount;
        private int interest_amount;
        private int interest_rate;
        private int restRateDays;
        private double sum_amount_year;
        private double sum_bonus_rate_year;
        private double sum_bonus_year;
        private double sum_rate_year;
        private double total_amount;
        private double total_rate;

        public double getBonusForYearPlan() {
            return bonusForYearPlan;
        }

        public void setBonusForYearPlan(double bonusForYearPlan) {
            this.bonusForYearPlan = bonusForYearPlan;
        }

        public double getBonusRateForYearPlan() {
            return bonusRateForYearPlan;
        }

        public void setBonusRateForYearPlan(double bonusRateForYearPlan) {
            this.bonusRateForYearPlan = bonusRateForYearPlan;
        }

        public double getBonus_amount() {
            return bonus_amount;
        }

        public void setBonus_amount(double bonus_amount) {
            this.bonus_amount = bonus_amount;
        }

        public double getBonus_rate() {
            return bonus_rate;
        }

        public void setBonus_rate(double bonus_rate) {
            this.bonus_rate = bonus_rate;
        }

        public int getHold_amount() {
            return hold_amount;
        }

        public void setHold_amount(int hold_amount) {
            this.hold_amount = hold_amount;
        }

        public int getInterest_amount() {
            return interest_amount;
        }

        public void setInterest_amount(int interest_amount) {
            this.interest_amount = interest_amount;
        }

        public int getInterest_rate() {
            return interest_rate;
        }

        public void setInterest_rate(int interest_rate) {
            this.interest_rate = interest_rate;
        }

        public int getRestRateDays() {
            return restRateDays;
        }

        public void setRestRateDays(int restRateDays) {
            this.restRateDays = restRateDays;
        }

        public double getSum_amount_year() {
            return sum_amount_year;
        }

        public void setSum_amount_year(double sum_amount_year) {
            this.sum_amount_year = sum_amount_year;
        }

        public double getSum_bonus_rate_year() {
            return sum_bonus_rate_year;
        }

        public void setSum_bonus_rate_year(double sum_bonus_rate_year) {
            this.sum_bonus_rate_year = sum_bonus_rate_year;
        }

        public double getSum_bonus_year() {
            return sum_bonus_year;
        }

        public void setSum_bonus_year(double sum_bonus_year) {
            this.sum_bonus_year = sum_bonus_year;
        }

        public double getSum_rate_year() {
            return sum_rate_year;
        }

        public void setSum_rate_year(double sum_rate_year) {
            this.sum_rate_year = sum_rate_year;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public double getTotal_rate() {
            return total_rate;
        }

        public void setTotal_rate(double total_rate) {
            this.total_rate = total_rate;
        }
    }

    @Override
    public String toString() {
        return "MainXgb1{" +
                "rtcode=" + rtcode +
                ", data=" + data +
                '}';
    }
}
