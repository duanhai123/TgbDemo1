package com.geebit.app1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 找回密码界面
 */
public class SafetyActivity extends Activity  {


    private CheckBox cb_safe;
    private CheckBox cb_email;
    private Button btn_next;
    private ImageView MsgSafe;
    private ImageView maskSafe;
    private ImageView emailSafe;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initData();
    }

    private void initData() {
        maskSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SafetyActivity.this,AskSafetyActivity.class));
               // finish();
            }
        });
        MsgSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SafetyActivity.this,MessageSafetyActivity.class));
                //finish();
            }
        });
        emailSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SafetyActivity.this,EmailSafetyActivity.class));
               // finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*
    初始化控件
     */
    private void initView() {
        MsgSafe = (ImageView) findViewById(R.id.iv_message_safety);
        maskSafe = (ImageView) findViewById(R.id.iv_ask_safety);
        emailSafe = (ImageView) findViewById(R.id.iv_email_safety);
        back = (ImageView) findViewById(R.id.iv_back);

    }


}
