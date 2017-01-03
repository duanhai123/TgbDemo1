package com.geebit.app1.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.geebit.app1.R;

/**
 * Created by DEll on 2017-01-03.
 */
public class PopWindowView extends PopupWindow {
    private static final String TAG = "FinishProjectPopupWindows";

    private View mView;



    public PopWindowView(int view,Activity context) {
        super(context);



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(view, null);
        init();

    }

    public void init() {
        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Animation);
        //实例化一个ColorDrawable颜色为半透明
        //int cor=0000;
        ColorDrawable dw = new ColorDrawable(0000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }


}
