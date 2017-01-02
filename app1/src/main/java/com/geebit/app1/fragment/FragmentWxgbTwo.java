package com.geebit.app1.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.geebit.app1.R;
import com.geebit.app1.activity.TwoChangeIntoActivity;
import com.geebit.app1.activity.TwoHoldingDetailActivity;

/**
 * Created by DEll on 2016-12-31.
 */
public class FragmentWxgbTwo extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RelativeLayout rContent;
    private View view;
    private Button mChangeinto;

    private Button mChangeinout;
    private SwipeRefreshLayout mSrl;

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
    }

    @Override
    public void initViewData() {
        rContent = (RelativeLayout) mActivity.findViewById(R.id.rl_content);
        mChangeinto = (Button) view.findViewById(R.id.btn_change_into);
        mChangeinout = (Button) view.findViewById(R.id.btn_change_inout);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.srl_xgb2);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_content:
                Intent intent = new Intent(mActivity, TwoHoldingDetailActivity.class);
                startActivity(intent);
                mActivity.finish();
            case R.id.btn_change_into:
                Intent intent1 = new Intent(mActivity, TwoChangeIntoActivity.class);
                startActivity(intent1);
                mActivity.finish();
                break;
            case R.id.btn_change_inout:

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
