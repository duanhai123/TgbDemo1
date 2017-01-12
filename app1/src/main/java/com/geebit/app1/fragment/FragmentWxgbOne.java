package com.geebit.app1.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.activity.ChangeInoutActivity;
import com.geebit.app1.activity.ChangeIntoActivity;
import com.geebit.app1.activity.HoldingDetailActivity;
import com.geebit.app1.activity.ReceiveDividendActivity;
import com.geebit.app1.bean.MainXgb1;
import com.geebit.app1.utils.CrmApiUtil;
import com.geebit.app1.utils.PrefUtils;
import com.geebit.app1.view.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


public class FragmentWxgbOne extends BaseFragment implements View.OnClickListener {


    private View view;
    private Button changeInto;
    private Button changeInout;
    private TextView mDividend;
    private TextView mDivDetail;
    private RelativeLayout mContent;
    private TextView mIncome;
    private SwipeRefreshLayout mSrl;
    private String uid;
    private double bonusForYearPlan;
    private double bonus_amount;
    private double bonus_rate;
    private double hold_amount;
    private double interest_amount;
    private double interest_rate;
    private int restRateDays;
    private double sum_amount_year;
    private double sum_bonus_rate_year;
    private double sum_bonus_year;
    private double sum_rate_year;
    private double total_amount;
    private double total_rate;
    private TextView tv_bfyp;
    private TextView tv_ba;
    private TextView tv_br;
    private TextView tv_ha;
    private TextView tv_ia;
    private TextView tv_ir;
    private TextView tv_rrd;
    private TextView tv_say;


    private TextView tv_sry;
    private TextView tv_ta;
    private TextView tv_tr;
    private TextView tv_brfyp;
    private double bonusRateForYearPlan;
    private ScrollView mScrollView;


    @Override
    public View initView() {
        view = View.inflate(mActivity, R.layout.fragment_wxgb1,null);

        return view;
    }

    @Override
    public void initData() {

        changeInto.setOnClickListener(this);
        changeInout.setOnClickListener(this);
        mDividend.setOnClickListener(this);
        mContent.setOnClickListener(this);
        //mSrl.setOnRefreshListener(this);

        showView();
        final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //initView();

                mSrl.setRefreshing(false);
            }
        };
      mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              mSrl.setRefreshing(true);
              onRefreshListener.onRefresh();
          }
      });
    }

    private void showView() {
        new Thread(){
            @Override
            public void run() {
                postJson();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_bfyp.setText(">=+"+bonusForYearPlan);
                        tv_ba.setText("(+"+bonus_amount+")");
                        System.out.println("bonusForYearPlan"+bonusForYearPlan);
                        tv_br.setText("+"+bonus_rate+"元");
                        tv_ha.setText("¥"+hold_amount);
                        tv_ia.setText("+"+interest_amount+"元");
                        tv_ir.setText("(+"+interest_rate+")");
                        tv_rrd.setText(restRateDays+"");
                        tv_say.setText("+"+sum_amount_year);

                        tv_sry.setText("(+"+sum_rate_year+")");
                        tv_ta.setText(">="+total_amount);
                        tv_tr.setText("(+"+total_rate+")");
                        tv_brfyp.setText("(+"+bonusRateForYearPlan+")");
                    }
                });
            }
        }.start();

    }

    @Override
    public void initViewData() {
        mScrollView = (ScrollView) view.findViewById(R.id.scrollView_xgb1);

        changeInto = (Button) view.findViewById(R.id.btn_change_into);
        changeInout = (Button) view.findViewById(R.id.btn_change_inout);
        mDividend = (TextView) view.findViewById(R.id.tv_receive_dividend);
        mContent = (RelativeLayout) view.findViewById(R.id.rl_content);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.srl_xgb1);
        tv_bfyp = (TextView)view. findViewById(R.id.bonusForYearPlan);
        tv_ba = (TextView)view. findViewById(R.id.bonus_amount);
        tv_br = (TextView)view. findViewById(R.id.bonus_rate);
        tv_ha = (TextView)view. findViewById(R.id.hold_amount);
        tv_ia = (TextView)view. findViewById(R.id.interest_amount);
        tv_ir = (TextView)view. findViewById(R.id.interest_rate);
        tv_rrd = (TextView)view. findViewById(R.id.restRateDays);
        tv_say = (TextView)view. findViewById(R.id.sum_amount_year);
        tv_brfyp = (TextView)view. findViewById(R.id.bonusRateForYearPlan
        );
        tv_sry = (TextView)view. findViewById(R.id.sum_rate_year);
        tv_ta = (TextView)view. findViewById(R.id.total_amount);
        tv_tr = (TextView)view. findViewById(R.id.total_rate);
        uid = MyApp.SP.getString("uid","");

    }

    private void postJson() {
        String batch_id = MyApp.SP.getString("batch_id", "");
        HashMap map = new HashMap();
        map.put("user_id",uid);
        map.put("batch_id",batch_id);
        JSONObject j = new JSONObject(map);
        String json = j.toString();
        String onlyJson = CrmApiUtil.postOnlyJson(
                "http://192.168.1.102:8080/api/orders/userProdStateRMB", json);
        Gson gson = new Gson();
        Type type = new TypeToken<MainXgb1>(){}.getType();
        MainXgb1 mainXgb1 =  gson.fromJson(onlyJson,type);
        //预计年度分红
        bonusForYearPlan = mainXgb1.getData().getBonusForYearPlan();

        //昨日分红利率
        bonus_amount = mainXgb1.getData().getBonus_amount();
        //昨日分红
        bonus_rate = mainXgb1.getData().getBonus_rate();
        //资产持有总额
        hold_amount = mainXgb1.getData().getHold_amount();
        //昨日收益
        interest_amount = mainXgb1.getData().getInterest_amount();
        //昨日利率
        interest_rate = mainXgb1.getData().getInterest_rate();
        //百日分红领取剩余时间
        restRateDays = mainXgb1.getData().getRestRateDays();
        //年度收益累计
        sum_amount_year = mainXgb1.getData().getSum_amount_year();
        //年度分红利率累计
        sum_bonus_rate_year = mainXgb1.getData().getSum_bonus_rate_year();
        //年度分红累计
        sum_bonus_year = mainXgb1.getData().getSum_bonus_year();
        //年度收益利率累计
        sum_rate_year = mainXgb1.getData().getSum_rate_year();
        //总收益=年度收益累计+年度分红累计
        total_amount = mainXgb1.getData().getTotal_amount();
        //总收益利率
        total_rate = mainXgb1.getData().getTotal_rate();
        //预计年度分红利率
        bonusRateForYearPlan = mainXgb1.getData().getBonusRateForYearPlan();
        MyApp.SP.edit().putFloat("hold_amout", (float) hold_amount).commit();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (restRateDays<100){
                    mDividend.setTextColor(Color.parseColor("#dadada"));
                    mDividend.setEnabled(false);
                }else {
                    mDividend.setTextColor(Color.parseColor("#f39700"));
                    mDividend.setEnabled(true);
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        String pwd;
        switch (view.getId()){
            case R.id.btn_change_into:
                //startActivity(new Intent(mActivity,ChangeIntoActivity.class));
                pwd = MyApp.SP.getString("pwd", "");
                String data = MyApp.SP.getString("data", "");
                String systemTime = getSystemTime(0);
                if (data.equals(systemTime)){
                    PrefUtils.remove("pwd",mActivity);
                    Intent intent = new Intent(mActivity, ChangeIntoActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                    return;
                }
                if (pwd.equals(uid)){
                    Toast.makeText(mActivity, "密码错误超过3次账号锁定了,请明天再试", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent = new Intent(mActivity, ChangeIntoActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                }
                break;
            case R.id.btn_change_inout:
                pwd = MyApp.SP.getString("pwd", "");
                String data1 = MyApp.SP.getString("data", "");
                String systemTime1 = getSystemTime(0);
                if (data1.equals(systemTime1)){
                    PrefUtils.remove("pwd",mActivity);
                    Intent intent = new Intent(mActivity, ChangeIntoActivity.class);
                    intent.putExtra("uid", uid);
                    startActivity(intent);
                    return;
                }
                if (pwd.equals(uid)){
                    Toast.makeText(mActivity, "密码错误超过3次账号锁定了,请明天再试", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent5 = new Intent(mActivity, ChangeInoutActivity.class);
                    intent5.putExtra("uid", uid);
                    startActivity(intent5);
                }
                break;
            case R.id.tv_receive_dividend:
                Intent intent4 = new Intent(mActivity, ReceiveDividendActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_content:
                Intent intent1 = new Intent(mActivity, HoldingDetailActivity.class);
                startActivity(intent1);
                break;
            /*case R.id.tv_dividend_detail:
                Intent intent2 = new Intent(mActivity, DividendDetailActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_income:
                Intent intent3 = new Intent(mActivity, IncomeDetailActivity.class);
                startActivity(intent3);
                break;*/
        }
    }

    private String getSystemTime(int i) {
        Calendar calendar   =   new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("MM-dd E");
        String mTime = formatter.format(date);
        return  mTime;
    }

}
