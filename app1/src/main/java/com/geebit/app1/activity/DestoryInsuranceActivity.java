package com.geebit.app1.activity;/* data: 2017-01-06
 * author: 段海鹏
 * ui: 历史保单
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geebit.app1.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DestoryInsuranceActivity extends BaseActivity{

    private View view;
    private RecyclerView desInsure;

    @Override
    protected void initoView() {
        desInsure = (RecyclerView) findViewById(R.id.rv_destory_insurance);
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList();
        for (int i = 0; i <4 ; i++) {
            list.add("111"+i);
        }
       LinearLayoutManager lay = new LinearLayoutManager(this);

        desInsure.setLayoutManager(lay);
        lay.setOrientation(LinearLayoutManager.VERTICAL);
        desInsure.setAdapter(new CommonAdapter(this,R.layout.item_des_insur,list) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        });
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_destory_insurance,null);
        return view;
    }
}
