package com.geebit.app1.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.geebit.app1.R;
import com.geebit.app1.activity.AssetDetailActivity;
import com.geebit.app1.activity.BorrowMoneyActivity;
import com.geebit.app1.activity.GbDetailActivity;
import com.geebit.app1.activity.InsuranceActivity;
import com.geebit.app1.activity.ProandlossDetailActivity;
import com.geebit.app1.activity.TwoChangeInoutActivity;
import com.geebit.app1.activity.TwoChangeIntoActivity;
import com.geebit.app1.activity.TwoHoldingDetailActivity;

/**
 * Created by DEll on 2016-12-31
 */
public class FragmentWxgbTwo extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RelativeLayout rContent;
    private View view;
    private Button mChangeinto;

    private Button mChangeinout;
    private SwipeRefreshLayout mSrl;
    private Button borrowMoney;
    private Button mInsurance;
    private LinearLayout llproDetail;
    private LinearLayout llopeDetail;
    private LinearLayout llgbDetail;
    @Override
    public View initView() {

        view = View.inflate(mActivity, R.layout.fragment_wxgb2,null);
        return view;
    }

    @Override
    public void initData() {
        rContent.setOnClickListener(this);
        mChangeinto.setOnClickListener(this);
        mChangeinout.setOnClickListener(this);
        mSrl.setOnRefreshListener(this);
        borrowMoney.setOnClickListener(this);
        mInsurance.setOnClickListener(this);
        llproDetail.setOnClickListener(this);
        llopeDetail.setOnClickListener(this);
        llgbDetail.setOnClickListener(this);
    }

    @Override
    public void initViewData() {
        rContent = (RelativeLayout) mActivity.findViewById(R.id.rl_content);
        mChangeinto = (Button) view.findViewById(R.id.btn_change_into);
        mChangeinout = (Button) view.findViewById(R.id.btn_change_inout);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.srl_xgb2);
        borrowMoney = (Button) view.findViewById(R.id.btn_borrow_money);
        mInsurance = (Button)view. findViewById(R.id.btn_is_insurance);
        llproDetail = (LinearLayout)view.findViewById(R.id.ll_proandloss_detail);
        llopeDetail = (LinearLayout)view.findViewById(R.id.ll_operate_detail);
        llgbDetail = (LinearLayout)view.findViewById(R.id.ll_gb_detail);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_content:
                Intent intent = new Intent(mActivity, TwoHoldingDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_proandloss_detail:
                Intent intent5 = new Intent(mActivity, ProandlossDetailActivity.class);
                startActivity(intent5);
                break;

            case R.id.ll_operate_detail://操作明细
                Intent intent6 = new Intent(mActivity, AssetDetailActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_change_into:
                Intent intent1 = new Intent(mActivity, TwoChangeIntoActivity.class);
                startActivity(intent1);

                break;
            case R.id.btn_change_inout:
                Intent intent2 = new Intent(mActivity, TwoChangeInoutActivity.class);
                startActivity(intent2);

                break;
            case R.id.btn_borrow_money:
                Intent intent3 = new Intent(mActivity, BorrowMoneyActivity.class);
                startActivity(intent3);
                break;

            case R.id.btn_is_insurance:
                Intent intent4 = new Intent(mActivity, InsuranceActivity.class);
                startActivity(intent4);
                    break;
            case R.id.ll_gb_detail:
                Intent intent7 = new Intent(mActivity, GbDetailActivity.class);
                startActivity(intent7);
                break;

        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSrl.setRefreshing(false);
            }
        },2000);
    }
}
