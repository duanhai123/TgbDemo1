package com.geebit.app1.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.CountDownTimerUtils;
import com.geebit.app1.utils.CrmApiUtil;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by DEll on 2016-12-05.
 * 通过短信找回密码
 */
public class MessageSafetyActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG ="" ;
    private ImageView iv_back;
    private EditText et_pin;
    private Button btn_pin;
    private TextView tv_pin;
    private Button btn_next;
    private View view;
    private String phone;
    private String serverPin;


    @Override
    protected void initoView() {
        iv_back = (ImageView)view. findViewById(R.id.iv_back);
        et_pin = (EditText) view.findViewById(R.id.et_pin);
        btn_pin = (Button)view. findViewById(R.id.btn_pin);
        tv_pin = (TextView)view. findViewById(R.id.tv_pin);
        btn_next = (Button)view. findViewById(R.id.btn_next);
    }

    protected void initData() {
        iv_back.setOnClickListener(this);

        btn_pin.setOnClickListener(this);

        btn_next.setOnClickListener(this);
        Intent intent = getIntent();
        phone = intent.getStringExtra("username");
        tv_pin.setText("请输入手机号"+getPhone(phone)+"收到的验证码");
    }

    /*
    初始化控件
     */
    public View initView() {

        view = View.inflate(this, R.layout.activity_message_safety,null);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pin:
                new Thread(){
                    @Override
                    public void run() {
                        postJson();
                    }
                }.start();

                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(btn_pin,30000,1000);
                countDownTimerUtils.onFinish();
                countDownTimerUtils.start();
                break;
            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_next:
                String mPin = et_pin.getText().toString().trim();
                if (mPin.isEmpty()){
                    Toast.makeText(MessageSafetyActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!mPin.equals(serverPin)){
                    Toast.makeText(MessageSafetyActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent = new Intent(MessageSafetyActivity.this, ReSetingPwdActivity.class);
                    intent.putExtra("username",phone);
                    startActivity(intent);
                    break;
                }
        }
    }

    private void postJson() {
        String url = "http://120.77.150.215:8080/xgb-api-server/user/sendChangePwdSMS";
        HashMap map = new HashMap();
        map.put("tel",phone);
        JSONObject jsonObject = new JSONObject(map);
        String json = jsonObject.toString();
        serverPin = CrmApiUtil.postOnlyJson(url, json);
        Log.i(TAG, "postJson: "+serverPin);
    }

    private String getPhone(String phone){

        //获取服务器的手机号显示到界面上

        String startPhone = phone.substring(0, 3);


        String endPhone = phone.substring(7, 11);
        String mPhone = startPhone+"****"+endPhone;
        return mPhone;
    }
}
