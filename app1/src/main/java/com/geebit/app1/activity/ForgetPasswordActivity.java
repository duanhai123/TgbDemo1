package com.geebit.app1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 */
public class ForgetPasswordActivity extends Activity {


    private static final String TAG ="tag" ;
    private Button btn_next;
    private ImageView iv_back;
    private EditText etPhone;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();



    }

    private void initData() {

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhone = etPhone.getText().toString().trim();
                Log.i(TAG, "onClick: "+mPhone);
                String num = "[1][358]\\d{9}";
                boolean matches = mPhone.matches(num);
                if (TextUtils.isEmpty(mPhone)) {
                    //startActivity(new Intent(ForgetPasswordActivity.this, SafetyActivity.class));
                    Toast.makeText(ForgetPasswordActivity.this, "手机号不能为空"+mPhone, Toast.LENGTH_SHORT).show();

                    return;
                } else if (!matches) {
                    Toast.makeText(ForgetPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    startActivity(new Intent(ForgetPasswordActivity.this, SafetyActivity.class));
                    finish();
                }
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void initView() {
        btn_next = (Button) findViewById(R.id.btn_next);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        etPhone = (EditText) findViewById(R.id.ec_edit_phone);
    }


}
