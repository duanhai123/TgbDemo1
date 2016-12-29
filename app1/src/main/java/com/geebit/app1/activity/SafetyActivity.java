package com.geebit.app1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.geebit.app1.R;


/**
 * Created by DEll on 2016-12-05.
 * 找回密码界面
 */
public class SafetyActivity extends BaseActivity  {


    private CheckBox cb_safe;
    private CheckBox cb_email;
    private Button btn_next;
    private ImageView MsgSafe;
    private ImageView maskSafe;
    private ImageView emailSafe;
    private ImageView back;
    private View view;
    private String username;


    @Override
    protected void initoView() {
        MsgSafe = (ImageView) view.findViewById(R.id.iv_message_safety);
        maskSafe = (ImageView)view. findViewById(R.id.iv_ask_safety);
        emailSafe = (ImageView)view. findViewById(R.id.iv_email_safety);
        back = (ImageView) view.findViewById(R.id.iv_back);
       Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    protected void initData() {
        maskSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafetyActivity.this,AskSafetyActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
               // finish();
            }
        });
        MsgSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafetyActivity.this,MessageSafetyActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                //finish();
            }
        });
        emailSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafetyActivity.this,EmailSafetyActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
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
    public View initView() {

        view = View.inflate(this, R.layout.activity_safety,null);
        return view;
    }


}
