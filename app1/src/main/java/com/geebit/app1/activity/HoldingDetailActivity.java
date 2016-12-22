package com.geebit.app1.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-21
 */
public class HoldingDetailActivity extends BaseActivity implements View.OnClickListener {

    private View view;

    private ImageView mBack;
    private TextView mSort;
    private PopupWindow window;
    private LinearLayout content2;
    private LinearLayout content1;
    private LinearLayout content3;
    private LinearLayout content4;


    @Override
    protected void initoView() {

        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mSort = (TextView) findViewById(R.id.tv_sort);
        content1 = (LinearLayout) view.findViewById(R.id.ll_content1);
        content2 = (LinearLayout) view.findViewById(R.id.ll_content2);
        content3 = (LinearLayout) view.findViewById(R.id.ll_content3);
        content4 = (LinearLayout) view.findViewById(R.id.ll_content4);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mSort.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_holding_detail, null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sort:
                init();
            break;
            case R.id.btn_All:
                window.dismiss();

                break;
            case R.id.btn_change_inout:
                window.dismiss();

                break;
            case R.id.btn_change_into:
                window.dismiss();
                break;
            case R.id.btn_receive_dividend:
                window.dismiss();
                break;
            case R.id.btn_send_dividend:
                window.dismiss();
                break;
            case R.id.btn_proceed:
                window.dismiss();
                break;
        }
    }

    private void init(){
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.holding_sort, null);
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
        window.showAtLocation(HoldingDetailActivity.this.findViewById(R.id.tv_sort),
                Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        Button proceed = (Button) view.findViewById(R.id.btn_proceed);
        Button receive_dividend = (Button) view.findViewById(R.id.btn_receive_dividend);
        Button change_inout = (Button) view.findViewById(R.id.btn_change_inout);
        Button send_dividend = (Button) view.findViewById(R.id.btn_send_dividend);
        Button All = (Button) view.findViewById(R.id.btn_All);
        Button change_into = (Button) view.findViewById(R.id.btn_change_into);
        proceed.setOnClickListener(this);
        receive_dividend.setOnClickListener(this);
      change_inout.setOnClickListener(this);
       send_dividend.setOnClickListener(this);
       All.setOnClickListener(this);
        change_into.setOnClickListener(this);
        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }
}
