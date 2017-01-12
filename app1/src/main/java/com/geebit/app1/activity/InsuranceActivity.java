package com.geebit.app1.activity;
/* data: 2017-01-06
 * author: 段海鹏
 * ui: 保险的页面有未买保险,已买保险,买部分保险
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.CustomKeyBoardUtil;
import com.geebit.app1.utils.CustomKeyboardView;
import com.geebit.app1.utils.PopWindowUtils;
import com.geebit.app1.utils.PopWindowView;
import com.geebit.app1.utils.SeekBarUtil;

public class InsuranceActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {
    private int count =3;
    private View view;
    private ImageView mBack;
    private TextView desInsurance;
    private EditText etInsurance;
    private SeekBar sbBorrowInsurance;
    private SeekBar sbInNumber;
    private TextView tvBorrowNumber;
    private TextView tvInNumber;
    private Button btnInsurance;
    private GradientDrawable drawable;
    private GradientDrawable drawable1;
    private PopWindowView popupWindows;
    private CheckBox cb;

    @Override
    protected void initoView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        desInsurance = (TextView) findViewById(R.id.tv_destory_insurance);
        etInsurance = (EditText) findViewById(R.id.et_insurance_number);
        sbBorrowInsurance = (SeekBar) findViewById(R.id.sb_borrow_number);
        sbInNumber = (SeekBar) findViewById(R.id.sb_insurance_number);
        tvBorrowNumber = (TextView) findViewById(R.id.tv_borrow_number);
        tvInNumber = (TextView) findViewById(R.id.tv_insurance_number);
        btnInsurance = (Button) findViewById(R.id.btn_insurance_enter);
        cb = (CheckBox) findViewById(R.id.cb_insurance);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        desInsurance.setOnClickListener(this);
        sbBorrowInsurance.setOnSeekBarChangeListener(this);
        sbInNumber.setOnSeekBarChangeListener(this);
        btnInsurance.setOnClickListener(this);
        etInsurance.addTextChangedListener(this);
        drawable = (GradientDrawable) btnInsurance.getBackground();
        drawable.setColor(Color.parseColor("#dadada"));
        btnInsurance.setEnabled(false);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_insurance,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_destory_insurance:
                Intent intent = new Intent(this,DestoryInsuranceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_insurance_enter:
                if (!cb.isChecked()){
                    Toast.makeText(InsuranceActivity.this, "请勾选本人已阅读并同意签署", Toast.LENGTH_SHORT).show();
                }else {
                    showPopWindow();
                }
                break;

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()){
            case R.id.sb_insurance_number:
                tvInNumber.setText("已投0."+i+"000฿");
                SeekBarUtil.setSeekBarGps(sbInNumber,tvInNumber);
                break;
            case R.id.sb_borrow_number:
                tvBorrowNumber.setText("已借"+i+".0000฿");
                SeekBarUtil.setSeekBarGps(sbBorrowInsurance,tvBorrowNumber);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    ///文本监听变化
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           String text =  charSequence.toString().trim();
        if (TextUtils.isEmpty(text)||text.startsWith("0")){
            drawable1 = (GradientDrawable) btnInsurance.getBackground();
            drawable1.setColor(Color.parseColor("#dadada"));
            btnInsurance.setEnabled(false);
        }else {
            drawable1 = (GradientDrawable) btnInsurance.getBackground();
            drawable1.setColor(Color.parseColor("#f39700"));
            btnInsurance.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    private void showPopWindow() {

        popupWindows = new PopWindowView(R.layout.two_input_pwd,this);
        popupWindows.showAtLocation(this.findViewById(R.id.btn_insurance_enter),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        init();
    }

    protected void init() {

        final View contentView = popupWindows.getContentView();
        TextView forgetPwd = (TextView) contentView.findViewById(R.id.tv_forget_pwd);
        TextView tv_title = (TextView) contentView.findViewById(R.id.tv_title);
        tv_title.setText("赔付险");
       ImageView closed = (ImageView) contentView.findViewById(R.id.iv_closed);
        forgetPwd.setOnClickListener(this);
        closed.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
       CustomKeyboardView customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {

            private PopupWindow finishProjectPopupWindows1;

            @Override
            public void inputHasOver(String text) {
                if ("123456".equals(text)){
                    Toast.makeText(InsuranceActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InsuranceActivity.this,InsuranceFinishActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    count--;

                    if (count > 0) {
                        popupWindows.dismiss();
                        finishProjectPopupWindows1 = PopWindowUtils.popWindow(R.style.Animation,InsuranceActivity.this,
                                 R.layout.input_forget,InsuranceActivity.this,0,0,R.id.btn_enter_change_into);

                        View contentView1 = finishProjectPopupWindows1.getContentView();
                       TextView pwd = (TextView) contentView1.findViewById(R.id.tv_pwd);

                        pwd.setText("资金密码不正确,你还可用输入" + count + "次");
                        TextView mForgetPwd = (TextView) contentView1.findViewById(R.id.tv_forget_pwd);
                        mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();
                                startActivity(new Intent(InsuranceActivity.this,ForgetPasswordActivity.class));
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
                                Toast.makeText(InsuranceActivity.this, "密码超过3次,账号被锁定,请联系客服", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(InsuranceActivity.this,MainActivity.class));
                            }
                        });

                    }

                }


            }
        });

    }


}
