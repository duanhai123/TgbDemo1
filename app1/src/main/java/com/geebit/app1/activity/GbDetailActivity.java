package com.geebit.app1.activity;/* data: 2017-01-07
 * author: 段海鹏
 * ui: gb总值持有明细
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.geebit.app1.R;
import com.geebit.app1.adapter.CommonAdapter;
import com.geebit.app1.adapter.DividerItemDecoration;
import com.geebit.app1.adapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GbDetailActivity extends BaseActivity {

    private View view;
    private RecyclerView mDetail;
    private CommonAdapter adapter;
    private ImageView mBack;

    @Override
    protected void initoView() {
        mDetail = (RecyclerView) findViewById(R.id.rv_gb_detail);
        mBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initData() {
        setAdapter();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setAdapter() {
        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        adapter = new CommonAdapter(this, R.layout.item_gb_detail,list) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDetail.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetail.setAdapter(adapter);
        mDetail.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_gb_detail,null);
        return view;
    }
}
