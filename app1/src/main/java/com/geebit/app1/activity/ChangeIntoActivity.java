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
import com.geebit.app1.utils.FinishProjectPopupWindows;
import com.geebit.app1.utils.FinishProjectPopupWindows1;

/**
 * Created by DEll on 2016-12-19.
 */
public class ChangeIntoActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private static final String TAG = "tag";
    private ImageView mBack;
    private View view;


    private Button enterInto;

    private FinishProjectPopupWindows finishProjectPopupWindows;
    private FinishProjectPopupWindows1 finishProjectPopupWindows1;
    private CustomKeyboardView customKeyboardView;
    private TextView mDestory;
    private EditText money;
    private String mMoney;
    private String mMoney1;


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
                    finishProjectPopupWindows = new FinishProjectPopupWindows(this, itemsOnClick);


                    finishProjectPopupWindows.showAtLocation(this.findViewById(R.id.btn_enter_change_into),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    init();
                }
                break;
            case R.id.tv_destory:
                Intent intent = new Intent(this,HoldingDetailActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void init() {

        final View contentView = finishProjectPopupWindows.getContentView();
        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
        customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                int count =3;
                if ("123456".equals(text)){
                    Toast.makeText(ChangeIntoActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                }else {
                    if (count > 0) {
                        finishProjectPopupWindows.dismiss();
                        finishProjectPopupWindows1 = new FinishProjectPopupWindows1(ChangeIntoActivity.this, itemsOnClick);
                        finishProjectPopupWindows1.showAtLocation(ChangeIntoActivity.this.findViewById(R.id.btn_enter_change_into),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        View contentView1 = finishProjectPopupWindows1.getContentView();
                        TextView pwd = (TextView) contentView1.findViewById(R.id.tv_pwd);
                        count--;
                        pwd.setText("资金密码不正确,你还可用输入" + count + "次");
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
                case R.id.tv_again:
                    finishProjectPopupWindows1.dismiss();

                    break;
            }
        }
    };


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

        if (text.isEmpty()||"0".equals(text)){
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

