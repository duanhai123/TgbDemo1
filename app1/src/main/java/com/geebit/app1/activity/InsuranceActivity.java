package com.geebit.app1.activity;
/* data: 2017-01-06
 * author: 段海鹏
 * ui: 保险的页面有未买保险,已买保险,买部分保险
 */

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.SeekBarUtil;

public class InsuranceActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private View view;
    private ImageView mBack;
    private TextView desInsurance;
    private EditText etInsurance;
    private SeekBar sbBorrowInsurance;
    private SeekBar sbInNumber;
    private TextView tvBorrowNumber;
    private TextView tvInNumber;
    private Button btnInsurance;

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
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        desInsurance.setOnClickListener(this);
        sbBorrowInsurance.setOnSeekBarChangeListener(this);
        sbInNumber.setOnSeekBarChangeListener(this);
        btnInsurance.setOnClickListener(this);
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
            case R.id.btn_insurance_enter:
               String mInsurance =  etInsurance.getText().toString().trim();
                if (TextUtils.isEmpty(mInsurance)||mInsurance.startsWith("0")){
                    Toast.makeText(InsuranceActivity.this, "请输入保额,不能小于0", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent = new Intent(this, InsuranceFinishActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
