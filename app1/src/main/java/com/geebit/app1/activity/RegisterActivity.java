package com.geebit.app1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.CountDownTimerUtils;
import com.geebit.app1.utils.CrmApiUtil;
import com.squareup.okhttp.MediaType;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;



/**
 * Created by DEll on 2016-12-05.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    private static final String TAG = "tag";

    TextView tvMessage;
    // 弹出框
    private ProgressDialog mDialog;
    // username 输入框
    private EditText mUsernameEdit;
    // 短信验证码输入框
    private EditText ec_edit_pin;
    // 登录密码输入框
    private EditText mPasswordEdit;
    //确认密码
    private EditText mEnterPwd;
    private int recLen = 10;
    String s = "";
    private TextView mSignUpBtn;
    // 注册按钮
    private Button mRegister;
    private String username;
    private String password;
    private String mEnterPwder;
    private EditText code;
    private CheckBox cbSystem;
    private String pwdPin;
    private String mCode;
    private ImageView back;
    private CheckBox cbAgree;
    private Button messsage;
    private TextView mScan;

    private Runnable runnable;
    private String serverPin ;
    private View view;



    @Override
    protected void initoView() {
        back = (ImageView) view.findViewById(R.id.iv_back);
        mUsernameEdit = (EditText) view.findViewById(R.id.ec_edit_username);
        ec_edit_pin = (EditText)view. findViewById(R.id.ec_edit_pin);
        mPasswordEdit = (EditText)view. findViewById(R.id.ec_edit_password);
        mEnterPwd = (EditText)view. findViewById(R.id.et_enter_pwd);
        code = (EditText) view.findViewById(R.id.ec_edit_code);
        cbSystem = (CheckBox) view.findViewById(R.id.cb_system);
        mRegister = (Button) view.findViewById(R.id.ec_btn_register);
        cbAgree = (CheckBox) view.findViewById(R.id.cb_agree);
        messsage = (Button) view.findViewById(R.id.btn_message);
        mScan = (TextView) view.findViewById(R.id.tv_scan);


    }

    protected void initData() {
        mRegister.setOnClickListener(this) ;
        back.setOnClickListener(this);
        messsage.setOnClickListener(this);
        mScan.setOnClickListener(this);

    }

    public View initView() {

        view = View.inflate(this, R.layout.activity_register,null);
        return view;
    }

    /**
     * 注册方法
     */
    private void register() {
        // 注册是耗时过程，所以要显示一个dialog来提示下用户
        password = mPasswordEdit.getText().toString().trim();
        username = mUsernameEdit.getText().toString().trim();
        pwdPin = ec_edit_pin.getText().toString().trim();
        mEnterPwder = mEnterPwd.getText().toString().trim();
        mCode = code.getText().toString().trim();
        String num = "[1][3587]\\d{9}";
        boolean matches = username.matches(num);
        Pattern pattern = Pattern.compile("[0-9]*");


        if (username.isEmpty() || password.isEmpty() || mEnterPwder.isEmpty()||pwdPin.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "账号和登录密码密码或者短信验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if (!cbSystem.isChecked()&&mCode.isEmpty()){
            Toast.makeText(RegisterActivity.this, "请选择邀请码", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!(password.equals(mEnterPwder))) {
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致请重新输入", Toast.LENGTH_SHORT).show();
            return;
        } else if (!matches) {
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.length()<6||pattern.matcher(password.substring(0,1)).matches()){
            Toast.makeText(RegisterActivity.this, "密码以字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
            return;
        }else if (!cbAgree.isChecked()){
            Toast.makeText(RegisterActivity.this, "请勾选我已阅读并且同意服务条款", Toast.LENGTH_SHORT).show();
        }else if(!(pwdPin.equals("123456"))){
            Toast.makeText(RegisterActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
        }
        else {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("注册中，请稍后...");
            mDialog.show();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!RegisterActivity.this.isFinishing()) {

                                mDialog.dismiss();
                            }

                        }

                    });

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!RegisterActivity.this.isFinishing()) {
                                mDialog.dismiss();

                            }
                        }
                    });
                    postRequest(password,username);
                }

            }).start();

    }
        }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ec_btn_register:
                register();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_message:

                username = mUsernameEdit.getText().toString().trim();
                String num = "[1][3587]\\d{9}";
                boolean matches = username.matches(num);

                if (username.isEmpty()||!matches){
                    Toast.makeText(RegisterActivity.this, "手机号码输入用误", Toast.LENGTH_SHORT).show();
                    //messsage.setEnabled(false);
                        return;
                }else {
                    messsage.setEnabled(true);
                    new Thread() {
                        @Override
                        public void run() {

                            postRequest1(username);

                        }
                    }.start();
                    CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(messsage,30000,1000);
                    countDownTimerUtils.onFinish();
                    countDownTimerUtils.start();



                }

                break;
            case  R.id.tv_scan:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
        }
    }
    /**
     * post请求后台注册接口
     * @param tell
     * @param pwd
     *
     */
    private void postRequest(String pwd,String tell)  {

        //建立请求表单，添加上传服务器的参数
        String url = "http://120.77.150.215:8080/xgb-api-server/user/register";
        HashMap map = new HashMap();
        map.put("tel",tell);
        map.put("password",pwd);
        JSONObject jsonObject = new JSONObject(map);
        String json = jsonObject.toString();
        String onlyJson = CrmApiUtil.postOnlyJson(url, json);

    }
    private void postRequest1(String tell){

        String url = "http://http://120.77.150.215:8080/xgb-api-server/user/sendRegisterSMS";
        HashMap map = new HashMap();
        map.put("tel",tell);
        JSONObject jsonObject = new JSONObject(map);
        String json = jsonObject.toString();
        String server = CrmApiUtil.postOnlyJson(url, json);

        try {
            JSONObject j = new JSONObject(server);
            serverPin = j.getString("result");
            Log.i(TAG, "postRequest1: "+ serverPin);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
