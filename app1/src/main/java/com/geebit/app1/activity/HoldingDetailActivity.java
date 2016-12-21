package com.geebit.app1.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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


    @Override
    protected void initoView() {

        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mSort = (TextView) findViewById(R.id.tv_sort);
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
        window.setAnimationStyle(R.style.Animation);
        // 在底部显示
        window.showAtLocation(HoldingDetailActivity.this.findViewById(R.id.tv_sort),
                Gravity.TOP, 120, 120);
        Button first = (Button) view.findViewById(R.id.btn_proceed);
        first.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    window.dismiss();
                System.out.println("第一个按钮被点击了");
            }
        });

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }
}
