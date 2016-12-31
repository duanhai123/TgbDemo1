package com.geebit.app1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geebit.app1.R;
import com.geebit.app1.utils.PopWindowUtils;

/**
 * Created by DEll on 2016-12-21
 * 持有明细2型
 */
public class TwoHoldingDetailActivity extends BaseActivity implements View.OnClickListener {

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


    @Override
    protected void initoView() {

        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mSort = (TextView) findViewById(R.id.tv_sort);

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
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_two_holding_detail, null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sort:
                //popwindow的初始化
                PopWindowUtils.popWindow(R.style.AnimationOut,this,R.layout.two_holding_sort,TwoHoldingDetailActivity.this,0,120,R.id.tv_sort);
            break;

        }
    }


}
