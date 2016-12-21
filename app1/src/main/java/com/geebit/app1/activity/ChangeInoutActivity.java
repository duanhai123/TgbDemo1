package com.geebit.app1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-21.
 */
public class ChangeInoutActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private ImageView mBack;
    private Button mChangeOut;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mChangeOut = (Button) view.findViewById(R.id.btn_enter_change_inout);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mChangeOut.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_change_inout,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter_change_inout:
                Intent intent = new Intent(this,ResultDetailActivity.class);
                startActivity(intent);

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
