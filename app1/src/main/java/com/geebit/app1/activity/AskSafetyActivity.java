package com.geebit.app1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 通过问题找回密码
 */
public class AskSafetyActivity extends Activity implements View.OnClickListener {


    private CheckBox cb_safe;
    private CheckBox cb_email;
    private Button btn_next;
    private ImageView back;
    private TextView tvAsk1;
    private TextView tvAsk2;
    private EditText etAsk1;
    private EditText etAsk2;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_safety);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();
    }

    private void initData() {
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        //调用服务器接口来显示用户设置的问题

    }

    /*
    初始化控件
     */
    private void initView() {
        back = (ImageView) findViewById(R.id.iv_back);
        tvAsk1 = (TextView) findViewById(R.id.tv_ask1);
        tvAsk2 = (TextView) findViewById(R.id.tv_ask2);
        etAsk1 = (EditText) findViewById(R.id.et_ask1);
        etAsk2 = (EditText) findViewById(R.id.et_ask2);
        next = (Button) findViewById(R.id.btn_next);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //调用服务器接口来显示用户设置的问题

            case R.id.btn_next:
                String et_askServer1 ="1";

                String et_askServer2 ="2";

                //获取用户输入的文本对比
                String mAsk1 = etAsk1.getText().toString().trim();
                String mAsk2 = etAsk2.getText().toString().trim();

                if (mAsk1.isEmpty()||mAsk2.isEmpty()){
                    Toast.makeText(AskSafetyActivity.this, "问题答案不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (mAsk1.equals(et_askServer1)&&mAsk2.equals(et_askServer2)){
                    startActivity(new Intent(this, ReSetingPwdActivity.class));
                    finish();

                }else {
                    Toast.makeText(AskSafetyActivity.this, "输入的问题答案不正确,请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
            case R.id.iv_back:
                finish();
        }
    }
}
