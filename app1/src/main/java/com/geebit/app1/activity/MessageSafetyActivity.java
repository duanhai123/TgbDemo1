package com.geebit.app1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 通过短信找回密码
 */
public class MessageSafetyActivity extends Activity implements View.OnClickListener {


    private static final String TAG ="" ;
    private ImageView iv_back;
    private EditText et_pin;
    private Button btn_pin;
    private TextView tv_pin;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_safety);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();
    }

    private void initData() {
        iv_back.setOnClickListener(this);

        btn_pin.setOnClickListener(this);

        btn_next.setOnClickListener(this);
        String phone = "13043898888";
        tv_pin.setText("请输入手机号"+getPhone(phone)+"收到的验证码");
    }

    /*
    初始化控件
     */
    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_pin = (EditText) findViewById(R.id.et_pin);
        btn_pin = (Button) findViewById(R.id.btn_pin);
        tv_pin = (TextView) findViewById(R.id.tv_pin);
        btn_next = (Button) findViewById(R.id.btn_next);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pin:

                break;
            case R.id.iv_back:

                finish();
                break;

            case R.id.btn_next:


                String serverPin = "1234";
                String mPin = et_pin.getText().toString().trim();
                if (mPin.isEmpty()){
                    Toast.makeText(MessageSafetyActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!mPin.equals(serverPin)){
                    Toast.makeText(MessageSafetyActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    startActivity(new Intent(MessageSafetyActivity.this, ReSetingPwdActivity.class));
                    break;
                }
        }
    }
    private String getPhone(String phone){

        //获取服务器的手机号显示到界面上

        String startPhone = phone.substring(0, 3);


        String endPhone = phone.substring(7, 11);
        String mPhone = startPhone+"****"+endPhone;
        return mPhone;
    }
}
