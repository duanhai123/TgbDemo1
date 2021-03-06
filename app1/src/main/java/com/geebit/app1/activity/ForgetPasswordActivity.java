package com.geebit.app1.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 忘记密码的页面
 */
public class ForgetPasswordActivity extends BaseActivity {


    private static final String TAG ="tag" ;
    private Button btn_next;
    private ImageView iv_back;
    private EditText etPhone;
    private String mPhone;
    private View view;



    @Override
    protected void initoView() {
        btn_next = (Button) view.findViewById(R.id.btn_next);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        etPhone = (EditText) view.findViewById(R.id.ec_edit_phone);
    }

    protected void initData() {

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
                    Intent intent = new Intent(ForgetPasswordActivity.this, SafetyActivity.class);
                    intent.putExtra("username",mPhone);
                    startActivity(intent);
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

    public View initView() {

        view = View.inflate(this, R.layout.activity_forgetpassword,null);
        return view;
    }


}
