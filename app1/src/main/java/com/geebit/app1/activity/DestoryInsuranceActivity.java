package com.geebit.app1.activity;
/* data: 2017-01-06
 * author: 段海鹏
 * ui: 历史保单
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.geebit.app1.R;
import com.geebit.app1.adapter.RecylerDesInsuranceAdapter;

import java.util.ArrayList;
import java.util.List;

public class DestoryInsuranceActivity extends BaseActivity{

    private View view;
    private RecyclerView desInsure;
    private ImageView mBack;

    @Override
    protected void initoView() {
        desInsure = (RecyclerView) findViewById(R.id.rv_destory_insurance);
        mBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initData() {
        setAdapter();
        onClick();

    }

    private void onClick() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setAdapter() {
        List<String> list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            list.add("111" + i);
        }
        LinearLayoutManager lay = new LinearLayoutManager(this);

        desInsure.setLayoutManager(lay);
        lay.setOrientation(LinearLayoutManager.VERTICAL);
        desInsure.setAdapter(new RecylerDesInsuranceAdapter(this, R.layout.item_des_insur,list));
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_destory_insurance,null);
        return view;
    }
}
