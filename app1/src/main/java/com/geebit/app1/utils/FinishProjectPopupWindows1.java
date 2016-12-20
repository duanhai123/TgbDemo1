package com.geebit.app1.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * Created by admin on 2016/12/19.
 */
public class FinishProjectPopupWindows1 extends PopupWindow {
    private static final String TAG = "FinishProjectPopupWindows";

    private View mView;

    private final TextView again;

    public FinishProjectPopupWindows1(Activity context, View.OnClickListener itemsOnClick) {
        super(context);



       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.input_forget, null);
        again = (TextView) mView.findViewById(R.id.tv_again);
        // 设置按钮监听
        again.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
       // btnSaveProject.setOnClickListener(itemsOnClick);
       // btnAbandonProject.setOnClickListener(itemsOnClick);

        init();

    }

    public void init() {
        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Animation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }


}

