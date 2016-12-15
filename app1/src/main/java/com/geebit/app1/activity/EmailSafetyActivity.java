package com.geebit.app1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 通过Email找回密码
 */
public class EmailSafetyActivity extends Activity implements View.OnClickListener {


    private ImageView iv_back;
    private EditText et_email;
    private Button next;
    private EditText etEmail;
    private String mEamil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_safety);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();
    }

    private void initData() {
        iv_back.setOnClickListener(this);
        next.setOnClickListener(this);
        etEmail.setHint("服务器获取设置ema");
        etEmail.setEnabled(false);
    }

    /*
    初始化控件
     */
    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_email = (EditText) findViewById(R.id.et_email);
        next = (Button) findViewById(R.id.btn_next);
        etEmail = (EditText) findViewById(R.id.et_email);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:

               // Toast.makeText(EmailSafetyActivity.this, "email"+mEamil, Toast.LENGTH_SHORT).show();
                Toast.makeText(EmailSafetyActivity.this, "邮件已经发送,请注意查收", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EmailSafetyActivity.this,ReSetingPwdActivity.class));
                finish();

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
