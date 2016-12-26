package com.geebit.app1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.CustomKeyBoardUtil;
import com.geebit.app1.utils.CustomKeyboardView;
import com.geebit.app1.utils.FinishProjectPopupWindows;
import com.geebit.app1.utils.FinishProjectPopupWindows1;

/**
 * Created by DEll on 2016-12-21.
 * 转出的页面
 */
public class ChangeInoutActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private CustomKeyboardView customKeyboardView;
    private View view;
    private ImageView mBack;
    private Button mChangeOut;
    private EditText outMoney;
    int count =3;
    private FinishProjectPopupWindows finishProjectPopupWindows;
    private FinishProjectPopupWindows1 finishProjectPopupWindows1;
    private TextView mDestory;
    private TextView forgetPwd;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mChangeOut = (Button) view.findViewById(R.id.btn_enter_change_inout);
        outMoney = (EditText) view.findViewById(R.id.et_money_inout);
        mDestory = (TextView) view.findViewById(R.id.tv_destory);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mChangeOut.setOnClickListener(this);
        mChangeOut.setEnabled(false);
        mChangeOut.setBackgroundColor(Color.parseColor("#dadada"));
        outMoney.addTextChangedListener(this);
        mDestory.setOnClickListener(this);

    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_change_inout,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter_change_inout:
                showPopWindow();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_destory:
                Intent intent = new Intent(this,HoldingDetailActivity.class);
                intent.putExtra("changeinout","changeinout");
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
        }
    }

    private void showPopWindow() {
        finishProjectPopupWindows = new FinishProjectPopupWindows(this, itemsOnClick);


        finishProjectPopupWindows.showAtLocation(this.findViewById(R.id.btn_enter_change_inout),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        init();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString().trim();
        if (text.isEmpty()||text.startsWith("0")){
            mChangeOut.setBackgroundColor(Color.parseColor("#dadada"));
            mChangeOut.setEnabled(false);
        }else {
            mChangeOut.setBackgroundColor(Color.parseColor("#f39700"));
            mChangeOut.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    private void init() {

        final View contentView = finishProjectPopupWindows.getContentView();
        forgetPwd = (TextView) contentView.findViewById(R.id.tv_forget_pwd);
        forgetPwd.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
        customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                if ("123456".equals(text)){
                    Toast.makeText(ChangeInoutActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeInoutActivity.this,ResultDetailActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    count--;

                    if (count>0){
                        finishProjectPopupWindows.dismiss();
                        finishProjectPopupWindows1 = new FinishProjectPopupWindows1(ChangeInoutActivity.this, itemsOnClick);


                        finishProjectPopupWindows1.showAtLocation(ChangeInoutActivity.this.findViewById(R.id.btn_enter_change_inout),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                        View contentView1 = finishProjectPopupWindows1.getContentView();
                        TextView mPwd = (TextView) contentView1.findViewById(R.id.tv_pwd);
                        mPwd.setText("资金密码不正确,你还可用输入" + count + "次");
                        TextView mForgetPwd = (TextView) contentView1.findViewById(R.id.tv_forget_pwd);
                        mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();
                                startActivity(new Intent(ChangeInoutActivity.this,ForgetPasswordActivity.class));
                                //finish();
                            }
                        });
                        TextView mAgain = (TextView) contentView1.findViewById(R.id.tv_again);
                        mAgain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();

                                showPopWindow();
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ChangeInoutActivity.this, "密码超过3次,账号被锁定,请联系客服", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangeInoutActivity.this,MainActivity.class));

                            }
                        });

                    }
                }
            }
        });
    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.closed:
                    finishProjectPopupWindows.dismiss();
                    break;

            }
        }
    };
}
