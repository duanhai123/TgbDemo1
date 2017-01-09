package com.geebit.app1.activity;
/* data: 2017-01-05
 * author: 段海鹏
 * ui: 利息明细的页面
 */

import android.view.View;
import android.widget.ImageView;

import com.geebit.app1.R;

public class IntersterItemActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private ImageView mBack;

    @Override
    protected void initoView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_interest_item,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
