package com.geebit.app1.activity;/* data: 2017-01-07
 * author: 段海鹏
 * ui: 
 */

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geebit.app1.R;
import com.geebit.app1.adapter.CommonAdapter;
import com.geebit.app1.adapter.DividerItemDecoration;
import com.geebit.app1.adapter.MultiItemTypeAdapter;
import com.geebit.app1.adapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProandlossDetailActivity extends BaseActivity implements View.OnClickListener, MultiItemTypeAdapter.OnItemClickListener {
    private List list;
    private List list1;
    private View view;
    private ImageView mBack;
    private TextView tvPost;
    private TextView tvCurrent;
    private LinearLayout llPost;
    private LinearLayout llCurrent;
    private RecyclerView rvProDetail;
    private RecyclerView rvProDetailPost;
    private CommonAdapter adapter;
   private CommonAdapter adapter1;
    private LinearLayout mLLCurrent;
    private LinearLayout mLLPost;

    @Override
    protected void initoView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        tvPost = (TextView) findViewById(R.id.tv_post_text);
        tvCurrent = (TextView) findViewById(R.id.tv_current_text);
        llPost = (LinearLayout) findViewById(R.id.ll_post_line);
        llCurrent = (LinearLayout) findViewById(R.id.ll_current_line);
        rvProDetail = (RecyclerView) findViewById(R.id.rv_pro_detail);
        rvProDetailPost = (RecyclerView) findViewById(R.id.rv_pro_detail_post);
        mLLCurrent = (LinearLayout) findViewById(R.id.ll_curent_content);
        mLLPost = (LinearLayout) findViewById(R.id.ll_post_content);
    }

    @Override
    protected void initData() {
        setAdapter();
        mBack.setOnClickListener(this);
        tvPost.setOnClickListener(this);
        tvCurrent.setOnClickListener(this);


    }

    private void setAdapter() {
        list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            list.add(""+i);

        }
        list1 = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list1.add(""+i);

        }
        adapter= new CommonAdapter(this,R.layout.item_pro_detail,list) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
        adapter1 = new CommonAdapter(this,R.layout.item_pro_detail,list1) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
        //setCurrent(adapter1);
        setCurrent(adapter,rvProDetail);

    }

    private void setCurrent(CommonAdapter adapter ,RecyclerView rvProDetail) {
        LinearLayoutManager lay = new LinearLayoutManager(this);
        rvProDetail.setLayoutManager(lay);
        lay.setOrientation(LinearLayoutManager.VERTICAL);
        rvProDetail.setAdapter(adapter);
        rvProDetail.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_proandloss_detail,null);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_post_text:
                llCurrent.setVisibility(View.INVISIBLE);
                llPost.setVisibility(View.VISIBLE);
                tvCurrent.setTextColor(Color.parseColor("#656565"));
                tvPost.setTextColor(Color.parseColor("#f39700"));
                mLLCurrent.setVisibility(View.GONE);
                mLLPost.setVisibility(View.VISIBLE);
                setCurrent(adapter1,rvProDetailPost);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_current_text:
                llCurrent.setVisibility(View.VISIBLE);
                llPost.setVisibility(View.INVISIBLE);
                tvPost.setTextColor(Color.parseColor("#656565"));
                tvCurrent.setTextColor(Color.parseColor("#f39700"));
                mLLCurrent.setVisibility(View.VISIBLE);
                mLLPost.setVisibility(View.GONE);
                setCurrent(adapter,rvProDetail);
                break;
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Intent intent = new Intent(this,GbDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
