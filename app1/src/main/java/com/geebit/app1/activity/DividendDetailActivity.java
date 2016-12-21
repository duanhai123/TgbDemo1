package com.geebit.app1.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.geebit.app1.R;
import com.geebit.app1.adpter.RecylerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DEll on 2016-12-21.
 */
public class DividendDetailActivity extends BaseActivity implements View.OnClickListener {
    private List<String> mDatas;
    private View view;
    private ImageView mBack;
    private RecyclerView mDestory;
    private RecylerAdapter recylerAdapter;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mDestory = (RecyclerView)view. findViewById(R.id.rv_destory);
        initDate();
        recylerAdapter = new RecylerAdapter(DividendDetailActivity.this,mDatas);

    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDestory.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
     //  mDestory.setAdapter(recylerAdapter);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_divident_detail,null);
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
    private void initDate() {
      String time = "2015-11-1";
      // String up =  "3.3";
      //  String up_float = "(3.34%)";
       // ProceedsDetail proceedsDetail = new ProceedsDetail();
      //  String time = proceedsDetail.getTime();
       // String up = proceedsDetail.getUp();
      //  String upFloat = proceedsDetail.getUp_float();
        mDatas = new ArrayList<>();
        for ( int i=0; i < 40; i++) {
            mDatas.add(time);
         // mDatas.add(up+i+"元");
        //  mDatas.add(up_float+i);
        }
    }
}
