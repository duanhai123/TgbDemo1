package com.geebit.app1.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geebit.app1.R;

import java.util.regex.Pattern;


/**
 * Created by DEll on 2016-12-05
 * 重置登录密码.
 */
public class ReSetingPwdActivity extends Activity implements View.OnClickListener, TextWatcher {


    private static final String TAG ="tag" ;
    private ImageView back;
    private Button btnHigh;
    private Button btnLower;
    private Button btnMiddle;
    private Button btnSubmit;

    private EditText etEnterNewpwd;
    private EditText etNewpwd;

    private String newPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseting_pwd);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.iv_back);
        btnHigh = (Button) findViewById(R.id.btn_high);
        btnLower = (Button) findViewById(R.id.btn_lower);
        btnMiddle = (Button) findViewById(R.id.btn_middle);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        etEnterNewpwd = (EditText) findViewById(R.id.et_enter_newpwd);
        etNewpwd = (EditText) findViewById(R.id.et_newpwd);



    }

    private void initData() {
        back.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        newPwd = etNewpwd.getText().toString().trim();
        etNewpwd.addTextChangedListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                String num = "[1][358]\\d{9}";
                Pattern pattern = Pattern.compile("[0-9]*");
                //提交到服务器修改密码,跳转到登录
                String mNewpwd = etNewpwd.getText().toString().trim();
                String mEnterNewpwd = etEnterNewpwd.getText().toString().trim();

                if (mNewpwd.isEmpty()||mEnterNewpwd.isEmpty()){
                    Toast.makeText(ReSetingPwdActivity.this, "密码,和确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (mNewpwd.length()<6||pattern.matcher(mNewpwd.substring(0,1)).matches()){
                    Toast.makeText(ReSetingPwdActivity.this,
                            "密码以字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!(mNewpwd.equals(mEnterNewpwd))){
                    Toast.makeText(ReSetingPwdActivity.this, "密码,和确认密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    //调用后台接口修改账号密码
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }


                break;
            case R.id.iv_back:
                finish();
                break;


        }
    }


    //文本输入前状态
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    //文本输入中状态
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length()<=10){
            Log.i(TAG, "onTextChanged: "+"低");
            btnHigh.setBackgroundColor(Color.WHITE);
            btnMiddle.setBackgroundColor(Color.WHITE);
            btnLower.setBackgroundColor(Color.RED);
        }else if (charSequence.length()>=14){
            Log.i(TAG, "onTextChanged: "+"高");
            btnHigh.setBackgroundColor(Color.RED);
            btnMiddle.setBackgroundColor(Color.WHITE);
            btnLower.setBackgroundColor(Color.WHITE);
        }else {
            Log.i(TAG, "onTextChanged: "+"中");
            btnHigh.setBackgroundColor(Color.WHITE);
            btnMiddle.setBackgroundColor(Color.RED);
            btnLower.setBackgroundColor(Color.WHITE);
        }

    }
    //文本输入后状态
    @Override
    public void afterTextChanged(Editable editable) {

    }
}