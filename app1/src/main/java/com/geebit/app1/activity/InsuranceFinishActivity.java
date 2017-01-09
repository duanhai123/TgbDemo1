package com.geebit.app1.activity;
/* data: 2017-01-06
 * author: 段海鹏
 * ui: 购买保障完成的页面
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geebit.app1.R;

public class InsuranceFinishActivity extends BaseActivity implements View.OnClickListener {
    private View view;
    private TextView mFinish;
    private ImageView mBack;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mFinish = (TextView) view.findViewById(R.id.tv_finish);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mFinish.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_insurance_finish,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }
}
