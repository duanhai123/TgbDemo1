package com.geebit.app1.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.geebit.app1.R;
import com.geebit.app1.activity.ChangeIntoActivity;


public class FragmentWxgbOne extends BaseFragment implements View.OnClickListener {


    private View view;
    private Button changeInto;


    @Override
    public View initView() {
        view = View.inflate(mActivity, R.layout.fragment_wxgb1,null);
        changeInto = (Button) view.findViewById(R.id.btn_change_into);

        return view;
    }

    @Override
    public void initData() {
        changeInto.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_change_into:
                startActivity(new Intent(mActivity,ChangeIntoActivity.class));
                break;
        }
    }
}
