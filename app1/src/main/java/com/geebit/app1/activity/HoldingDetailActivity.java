package com.geebit.app1.activity;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-21
 */
public class HoldingDetailActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private View view;

    private ImageView mBack;
    private TextView mSort;

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
                getPopupWindow();
                popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);
                break;
        }
    }

    /**
     * 创建PopupWindow
     */
    protected void initPopuptWindow() {
        // TODO Auto-generated method stub
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = getLayoutInflater().inflate(R.layout.holding_sort, null,
                false);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, 200, WindowManager.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
        popupWindow.setAnimationStyle(R.style.Animation);
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
    }

    /***
     * 获取PopupWindow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

}
