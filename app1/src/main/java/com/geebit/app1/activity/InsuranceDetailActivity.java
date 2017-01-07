package com.geebit.app1.activity;
/* data: 2017-01-06
 * author: 段海鹏
 * ui: 已买保险的保单详情页面
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geebit.app1.R;

public class InsuranceDetailActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private ImageView mBack;
    private TextView mShare;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mShare = (TextView) view.findViewById(R.id.tv_share);
    }

    @Override
    protected void initData() {
            mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_insurance_detail,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:

                break;
        }
    }
}
