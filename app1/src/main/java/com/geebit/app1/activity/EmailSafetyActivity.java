package com.geebit.app1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 通过Email找回密码
 */
public class EmailSafetyActivity extends BaseActivity implements View.OnClickListener {


    private ImageView iv_back;
    private EditText et_email;
    private Button next;
    private EditText etEmail;
    private String mEamil;
    private View view;


    @Override
    protected void initoView() {
        iv_back = (ImageView)view. findViewById(R.id.iv_back);
        et_email = (EditText) view.findViewById(R.id.et_email);
        next = (Button) view.findViewById(R.id.btn_next);
        etEmail = (EditText)view. findViewById(R.id.et_email);
    }

    protected void initData() {
        iv_back.setOnClickListener(this);
        next.setOnClickListener(this);
        etEmail.setHint("服务器获取设置ema");
        etEmail.setEnabled(false);
    }

    /*
    初始化控件
     */
    public View initView() {
        view = View.inflate(this, R.layout.activity_email_safety,null);


        return view;
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
