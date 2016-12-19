package com.geebit.app1.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-19.
 */
public class ChangeIntoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private View view;
    private LinearLayout inputPwd;
    private Button enterInto;



    @Override
    protected void initoView() {
         mBack = (ImageView)view. findViewById(R.id.iv_back);
        inputPwd = (LinearLayout) view.findViewById(R.id.ll_input_pwd);
        enterInto = (Button)view. findViewById(R.id.btn_enter_change_into);

    }

    @Override
    protected void initData() {

        mBack.setOnClickListener(this);
        enterInto.setOnClickListener(this);
    }

    @Override
    public View initView() {

        view = View.inflate(this, R.layout.activity_change_into,null);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_enter_change_into:

                inputPwd.setVisibility(View.VISIBLE);

                break;
        }
    }


}
