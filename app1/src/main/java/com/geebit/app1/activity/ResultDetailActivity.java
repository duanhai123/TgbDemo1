package com.geebit.app1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-21.
 * 转出结果完成的页面
 */
public class ResultDetailActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private ImageView mBack;
    private Button mChangeOut;
    private TextView mFinish;
    private TextView tvUsable;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mFinish = (TextView) view.findViewById(R.id.tv_finish);
        tvUsable = (TextView) view.findViewById(R.id.tv_usable);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mFinish.setOnClickListener(this);
        tvUsable.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_result_detail,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.tv_usable:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
