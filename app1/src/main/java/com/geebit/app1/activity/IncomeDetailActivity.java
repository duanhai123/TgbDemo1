package com.geebit.app1.activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geebit.app1.R;
import com.geebit.app1.adapter.DividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DEll on 2016-12-21.
 * 收益明细的页面
 */
public class IncomeDetailActivity extends BaseActivity implements View.OnClickListener {
    private List<String> mDatas;
    private View view;
    private ImageView mBack;
    private RecyclerView mDestory;
    private TextView mScreen;
    private PopupWindow window;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mDestory = (RecyclerView)view. findViewById(R.id.rv_destory);
        mScreen = (TextView) view.findViewById(R.id.tv_screen);
        //recylerAdapter = new RecylerAdapter(this,R.layout.item_divdent_detail,mDatas);

    }

    @Override
    protected void initData() {
        init();
        mBack.setOnClickListener(this);
        mScreen.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDestory.setLayoutManager(layoutManager);
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       mDestory.setAdapter(new CommonAdapter(this,R.layout.item_divdent_detail,mDatas) {
           @Override
           protected void convert(ViewHolder holder, Object o, int position) {
           }
       });
        mDestory.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }

    private void init() {
        mDatas = new ArrayList<>();
        for (int i = 0 ;i<40;i++){
            mDatas.add("111");
        }
    }


    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_income_detail,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_screen:
                popWindow();
                break;
            case R.id.btn_income_detail:
                window.dismiss();
                break;
            case R.id.btn_dividend_detail:
                startActivity(new Intent(this,DividendDetailActivity.class));
                window.dismiss();
                break;
        }
    }
    private void popWindow(){
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dividend_screen_popwindos, null);
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.AnimationOut);
        // 在底部显示
        window.showAtLocation(IncomeDetailActivity.this.findViewById(R.id.tv_screen),
                Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        Button mDividend = (Button) view.findViewById(R.id.btn_dividend_detail);
        Button mIncome = (Button) view.findViewById(R.id.btn_income_detail);

        mDividend.setOnClickListener(this);
        mIncome.setOnClickListener(this);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }
}
