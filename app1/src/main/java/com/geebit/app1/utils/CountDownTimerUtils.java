package com.geebit.app1.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.geebit.app1.R;

/**
 * Created by DEll on 2016-12-15.
 * 短信倒计时的工具类
 */
public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;
    public CountDownTimerUtils(TextView textView,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
    //设置不可以点击
        mTextView.setClickable(false);
        //设置倒计时时间
        mTextView.setText(millisUntilFinished/1000+" 后可重新发送");
        //设置按钮为灰色的,不可以点击的
        mTextView.setBackgroundResource(R.drawable.btn_shape_none);
        //获取按钮上的文字
        SpannableString spannableString = new SpannableString(
                mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        /*
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        //将倒计时的时间设置为红色
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {

        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.btn_shape);  //还原背景色
    }
}
