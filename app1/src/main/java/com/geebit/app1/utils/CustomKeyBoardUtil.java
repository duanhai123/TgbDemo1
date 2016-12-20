package com.geebit.app1.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者：wh
 * 创建时间：2016/8/23 09:59
 */
/*  用法示例
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_input);
        CustomKeyboardView customKeyboardView = (CustomKeyboardView) findViewById(R.id.custom_keyboard_view);
        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                Toast.makeText(MainActivity.this, "输入完成:" + text, Toast.LENGTH_LONG).show();
            }
        });
    }*/
public class CustomKeyBoardUtil {
    private Context mContext;
    private InputFinishListener mInputOver;
    private TextView[] textViews = new TextView[6];
    private LinearLayout mLayoutParent;
    private CustomKeyboardView mKeyboardView;

    //传入自定义的键盘和LinearLayout（密码的父布局）
    public CustomKeyBoardUtil(Context context, LinearLayout layoutParent, CustomKeyboardView keyboardView, InputFinishListener inputOver) {
        this.mContext = context;
        this.mLayoutParent = layoutParent;
        this.mInputOver = inputOver;
        this.mKeyboardView = keyboardView;
        initTextViews();

    }

    /**
     * 初始化输入框
     */
    private void initTextViews() {
        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = new TextView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            textViews[i].setLayoutParams(params);
            mLayoutParent.addView(textViews[i]);
            textViews[i].setTransformationMethod(PasswordTransformationMethod.getInstance());
            textViews[i].setGravity(Gravity.CENTER);
            textViews[i].setTextSize(30);
            if (i < textViews.length - 1) {
                View view = new View(mContext);
                LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(viewParams);
                view.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.secondary_text_dark));
                mLayoutParent.addView(view);
            }
        }
        setOnKeysListener();
    }

    /**
     * mKeyboardView的点击事件，分别是0123456789和删除键。
     */
    private void setOnKeysListener() {
        mKeyboardView.getTv0().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("0");
            }
        });
        mKeyboardView.getTv1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("1");
            }
        });
        mKeyboardView.getTv2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("2");
            }
        });
        mKeyboardView.getTv3().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("3");
            }
        });
        mKeyboardView.getTv4().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("4");
            }
        });
        mKeyboardView.getTv5().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("5");
            }
        });
        mKeyboardView.getTv6().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("6");
            }
        });
        mKeyboardView.getTv7().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("7");
            }
        });
        mKeyboardView.getTv8().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("8");
            }
        });
        mKeyboardView.getTv9().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextView("9");
            }
        });
        mKeyboardView.getIvDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTextView();
            }
        });
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 输入密码
     */
    private void inputTextView(String code) {
        for (int i = 0; i < textViews.length; i++) {
            TextView tv = textViews[i];
            if (tv.getText().toString().equals("")) {
                tv.setText(code);
                if (i == textViews.length - 1) {
                    mInputOver.inputHasOver(getInputText());//当密码输入到最后一个了，回调此方法
                }
                return;
            }
        }
    }

    /**
     * 清空所有密码
     */
    public void clearTextView() {
        for (int i = textViews.length - 1; i >= 0; i--) {
            TextView tv = textViews[i];
            if (!tv.getText().toString().equals("")) {
                tv.setText("");
            }
            mLayoutParent.invalidate();
        }
    }

    /**
     * 删除最后一格的密码
     */
    private void deleteTextView() {
        for (int i = textViews.length - 1; i >= 0; i--) {
            TextView tv = textViews[i];
            if (!tv.getText().toString().equals("")) {
                tv.setText("");
                return;
            }
        }
    }

    /**
     * 获取输入的密码
     */
    private String getInputText() {
        StringBuffer sb = new StringBuffer();
        for (TextView tv : textViews) {
            sb.append(tv.getText().toString());
        }
        return sb.toString();
    }

    /**
     * 输入监听
     */
    public interface InputFinishListener {
        void inputHasOver(String text);
    }
}
