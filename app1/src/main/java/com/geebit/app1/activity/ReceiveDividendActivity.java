package com.geebit.app1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-21.
 * 领取分红的页面
 */
public class ReceiveDividendActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private ImageView mBack;
    private TextView mDestory;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mDestory = (TextView) view.findViewById(R.id.tv_destory);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mDestory.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_receive_dividend,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_destory:
                Intent intent = new Intent(this,HoldingDetailActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
