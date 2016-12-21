package com.geebit.app1.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geebit.app1.R;
import com.geebit.app1.activity.ChangeInoutActivity;
import com.geebit.app1.activity.ChangeIntoActivity;
import com.geebit.app1.activity.HoldingDetailActivity;
import com.geebit.app1.activity.ReceiveDividendActivity;


public class FragmentWxgbOne extends BaseFragment implements View.OnClickListener {


    private View view;
    private Button changeInto;
    private Button changeInout;
    private TextView mDividend;
    private TextView mHolding;


    @Override
    public View initView() {
        view = View.inflate(mActivity, R.layout.fragment_wxgb1,null);
        changeInto = (Button) view.findViewById(R.id.btn_change_into);
        changeInout = (Button) view.findViewById(R.id.btn_change_inout);
        mDividend = (TextView) view.findViewById(R.id.tv_receive_dividend);
        mHolding = (TextView) view.findViewById(R.id.tv_holding);
        return view;
    }

    @Override
    public void initData() {
        changeInto.setOnClickListener(this);
        changeInout.setOnClickListener(this);
        mDividend.setOnClickListener(this);
        mHolding.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_change_into:
                startActivity(new Intent(mActivity,ChangeIntoActivity.class));

                break;
            case R.id.btn_change_inout:
                startActivity(new Intent(mActivity,ChangeInoutActivity.class));

                break;
            case R.id.tv_receive_dividend:
                Intent intent = new Intent(mActivity, ReceiveDividendActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_holding:
                Intent intent1 = new Intent(mActivity, HoldingDetailActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
