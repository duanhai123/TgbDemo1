package com.geebit.app1.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * 作者：wh
 * 创建时间：2016/8/22 23:49
 */
public class CustomKeyboardView extends RelativeLayout {
    Context context;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private ImageView ivDelete;

    public CustomKeyboardView(Context context) {

        this(context, null);
    }

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = View.inflate(context, R.layout.custom_keyboard, null);
        tv0 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys0);
        tv1 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys1);
        tv2 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys2);
        tv3 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys3);
        tv4 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys4);
        tv5 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys5);
        tv6 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys6);
        tv7 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys7);
        tv8 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys8);
        tv9 = (TextView) view.findViewById(R.id.tv_custom_keyboard_keys9);
        ivDelete = (ImageView) view.findViewById(R.id.iv_custom_keyboard_keys_delete);

        addView(view);      //必须要，不然不显示控件
    }

    public TextView getTv0() {
        return tv0;
    }

    public TextView getTv1() {
        return tv1;
    }

    public TextView getTv2() {
        return tv2;
    }

    public TextView getTv3() {
        return tv3;
    }

    public TextView getTv4() {
        return tv4;
    }

    public TextView getTv5() {
        return tv5;
    }

    public TextView getTv6() {
        return tv6;
    }

    public TextView getTv7() {
        return tv7;
    }

    public TextView getTv8() {
        return tv8;
    }

    public TextView getTv9() {
        return tv9;
    }

    public ImageView getIvDelete() {
        return ivDelete;
    }
}
