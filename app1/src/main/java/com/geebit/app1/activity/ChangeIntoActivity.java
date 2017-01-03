package com.geebit.app1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.geebit.app1.utils.FinishProjectPopupWindows1;
import com.geebit.app1.utils.PopWindowView;

/**
 * Created by DEll on 2016-12-19.
 * 转入的页面
 */
public class ChangeIntoActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private static final String TAG = "tag";
    private ImageView mBack;
    private View view;
    int count =3;
    private Button enterInto;
    private PopWindowView finishProjectPopupWindows;
    private FinishProjectPopupWindows1 finishProjectPopupWindows1;
    private CustomKeyboardView customKeyboardView;
    private TextView mDestory;
    private EditText money;
    private String mMoney;
    private String mMoney1;
    private TextView pwd;
    private TextView forgetPwd;
    private ImageView closed;


    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);

        enterInto = (Button) view.findViewById(R.id.btn_enter_change_into);

        mDestory = (TextView) view.findViewById(R.id.tv_destory);
        money = (EditText)view. findViewById(R.id.et_money);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        enterInto.setOnClickListener(this);
        mDestory.setOnClickListener(this);
        money.addTextChangedListener(this);
         mMoney =  enterInto.getText().toString().trim();
        enterInto.setBackgroundColor(Color.parseColor("#dadada"));
        enterInto.setEnabled(false);

    }

    @Override
    public View initView() {

        view = View.inflate(this, R.layout.activity_change_into, null);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_enter_change_into:

                int money1 = Integer.parseInt(money.getText().toString().trim());
                if (money1>100){
                Toast.makeText(ChangeIntoActivity.this, "金额不够请充值", Toast.LENGTH_SHORT).show();

                }else {
                    showPopWindow();
                }
                break;
            case R.id.tv_destory:
                Intent intent = new Intent(this,HoldingDetailActivity.class);
                intent.putExtra("changeinto","changeinto");
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                finish();
                break;
            case R.id.closed:
                finishProjectPopupWindows.dismiss();
                break;
            case R.id.custom_keyboard_view:
                finishProjectPopupWindows.dismiss();
                break;
        }

    }

    private void showPopWindow() {
        finishProjectPopupWindows = new PopWindowView(R.layout.input_pwd,this);


        finishProjectPopupWindows.showAtLocation(this.findViewById(R.id.btn_enter_change_into),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        init();
    }

    private void init() {

        final View contentView = finishProjectPopupWindows.getContentView();
        forgetPwd = (TextView) contentView.findViewById(R.id.tv_forget_pwd);
        closed = (ImageView) contentView.findViewById(R.id.closed);
        forgetPwd.setOnClickListener(this);
        closed.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
        customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                if ("123456".equals(text)){
                    Toast.makeText(ChangeIntoActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeIntoActivity.this,ChangeIntoFinish.class);
                    startActivity(intent);
                    finish();
                }else {

                    count--;

                    if (count > 0) {
                        finishProjectPopupWindows.dismiss();
                        finishProjectPopupWindows1 = new FinishProjectPopupWindows1(ChangeIntoActivity.this);
                        finishProjectPopupWindows1.showAtLocation(ChangeIntoActivity.this.findViewById(R.id.btn_enter_change_into),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        View contentView1 = finishProjectPopupWindows1.getContentView();
                        pwd = (TextView) contentView1.findViewById(R.id.tv_pwd);

                       pwd.setText("资金密码不正确,你还可用输入" + count + "次");
                        TextView mForgetPwd = (TextView) contentView1.findViewById(R.id.tv_forget_pwd);
                        mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();
                                startActivity(new Intent(ChangeIntoActivity.this,ForgetPasswordActivity.class));
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


                    }


                    else {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(ChangeIntoActivity.this, "密码超过3次,账号被锁定,请联系客服", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(ChangeIntoActivity.this,MainActivity.class));

                           }
                       });

                    }

                }


            }
        });

    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text1 = charSequence.toString().trim();
        Log.i(TAG, "beforeTextChanged: "+text1);
        if (text1.isEmpty()){
           // enterInto.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString().trim();

        if (text.isEmpty()||text.startsWith("0")){
            enterInto.setBackgroundColor(Color.parseColor("#dadada"));
            enterInto.setEnabled(false);
        }else {
            enterInto.setBackgroundColor(Color.parseColor("#f39700"));
            enterInto.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

        }
    }

