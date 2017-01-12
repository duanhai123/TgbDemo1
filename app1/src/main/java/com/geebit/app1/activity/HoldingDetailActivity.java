package com.geebit.app1.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geebit.app1.R;
import com.geebit.app1.view.MyApp;

/**
 * Created by DEll on 2016-12-21
 * 持有明细1型
 */
public class HoldingDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "tag";
    private View view;

    private ImageView mBack;
    private TextView mSort;
    private PopupWindow window;
    private LinearLayout mIncome;
    private LinearLayout mChangein;
    private LinearLayout mChangeout;
    private LinearLayout mRecdiv;
    private LinearLayout mSenddiv;
    private LinearLayout llContentInto;


    @Override
    protected void initoView() {

        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mSort = (TextView) findViewById(R.id.tv_sort);
        mIncome = (LinearLayout) view.findViewById(R.id.ll_content_income);
        mChangein = (LinearLayout) view.findViewById(R.id.ll_content_changeinto);
        mChangeout = (LinearLayout) view.findViewById(R.id.ll_content_changeinout);
        mRecdiv = (LinearLayout) view.findViewById(R.id.ll_content_receive_dividend);
        mSenddiv = (LinearLayout) view.findViewById(R.id.ll_content_send_dividend);
        defaults();
        llContentInto = (LinearLayout)view. findViewById(R.id.ll_content_into);

    }
    //默认选择的排列
    private void defaults() {

        Intent intent = getIntent();
        String changeinto = intent.getStringExtra("changeinto");
        String changeinout = intent.getStringExtra("changeinout");
        if ("changeinto".equals(changeinto)) {
            mIncome.setVisibility(View.INVISIBLE);
            mChangein.setVisibility(View.VISIBLE);
            mChangeout.setVisibility(View.INVISIBLE);
            mRecdiv.setVisibility(View.INVISIBLE);
            mSenddiv.setVisibility(View.INVISIBLE);
        } else if ("changeinout".equals(changeinout)) {
            mIncome.setVisibility(View.INVISIBLE);
            mChangein.setVisibility(View.INVISIBLE);
            mChangeout.setVisibility(View.VISIBLE);
            mRecdiv.setVisibility(View.INVISIBLE);
            mSenddiv.setVisibility(View.INVISIBLE);
        } else {
            mIncome.setVisibility(View.INVISIBLE);
            mChangein.setVisibility(View.INVISIBLE);
            mChangeout.setVisibility(View.INVISIBLE);
            mRecdiv.setVisibility(View.VISIBLE);
            mSenddiv.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mSort.setOnClickListener(this);
        llContentInto.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_holding_detail, null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sort:
                init();
            break;
            case R.id.btn_All:
                window.dismiss();
                mIncome.setVisibility(View.VISIBLE);
                mChangein.setVisibility(View.INVISIBLE);
                mChangeout.setVisibility(View.INVISIBLE);
                mRecdiv.setVisibility(View.INVISIBLE);
                mSenddiv.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_change_inout:
                window.dismiss();
                mIncome.setVisibility(View.INVISIBLE);
                mChangein.setVisibility(View.INVISIBLE);
                mChangeout.setVisibility(View.VISIBLE);
                mRecdiv.setVisibility(View.INVISIBLE);
                mSenddiv.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_change_into:
                window.dismiss();
                mIncome.setVisibility(View.INVISIBLE);
                mChangein.setVisibility(View.VISIBLE);
                mChangeout.setVisibility(View.INVISIBLE);
                mRecdiv.setVisibility(View.INVISIBLE);
                mSenddiv.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_receive_dividend:
                window.dismiss();
                defaults();
                break;
            case R.id.btn_send_dividend:
                window.dismiss();
                mIncome.setVisibility(View.INVISIBLE);
                mChangein.setVisibility(View.INVISIBLE);
                mChangeout.setVisibility(View.INVISIBLE);
                mRecdiv.setVisibility(View.INVISIBLE);
                mSenddiv.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_proceed:
                window.dismiss();
                mIncome.setVisibility(View.VISIBLE);
                mChangein.setVisibility(View.INVISIBLE);
                mChangeout.setVisibility(View.INVISIBLE);
                mRecdiv.setVisibility(View.INVISIBLE);
                mSenddiv.setVisibility(View.INVISIBLE);

                break;
            case R.id.ll_content_into:
                String money = MyApp.SP.getString("into", "");
                Intent intent = new Intent(this,ChangeIntoFinish.class);
                intent.putExtra("getmoney",money);
                startActivity(intent);
                break;
        }
    }
    //popwindow的初始化
    private void init(){
       // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.holding_sort, null);
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.AnimationOut);
        // 在底部显示
        window.showAtLocation(HoldingDetailActivity.this.findViewById(R.id.tv_sort),
                Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        //PopWindowUtils.popWindow(this,R.layout.holding_sort,HoldingDetailActivity.this,0,120,R.id.tv_sort);
        Button proceed = (Button) view.findViewById(R.id.btn_proceed);
        Button receive_dividend = (Button) view.findViewById(R.id.btn_receive_dividend);
        Button change_inout = (Button) view.findViewById(R.id.btn_change_inout);
        Button send_dividend = (Button) view.findViewById(R.id.btn_send_dividend);
        Button All = (Button) view.findViewById(R.id.btn_All);
        Button change_into = (Button) view.findViewById(R.id.btn_change_into);
        proceed.setOnClickListener(this);
        receive_dividend.setOnClickListener(this);
        change_inout.setOnClickListener(this);
        send_dividend.setOnClickListener(this);
        All.setOnClickListener(this);
        change_into.setOnClickListener(this);

    }
}
