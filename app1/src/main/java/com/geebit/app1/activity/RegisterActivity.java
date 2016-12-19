package com.geebit.app1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.CountDownTimerUtils;

import java.util.regex.Pattern;


/**
 * Created by DEll on 2016-12-05.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {


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
    private TextView messsage;
    private Runnable runnable;
    private String serverPin;
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
        messsage = (TextView) view.findViewById(R.id.tv_message);
    }

    protected void initData() {
        mRegister.setOnClickListener(this) ;
        back.setOnClickListener(this);
        messsage.setOnClickListener(this);


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
        username = mUsernameEdit.getText().toString().trim();
        pwdPin = ec_edit_pin.getText().toString().trim();
        password = mPasswordEdit.getText().toString().trim();
        mEnterPwder = mEnterPwd.getText().toString().trim();
        mCode = code.getText().toString().trim();

        String num = "[1][358]\\d{9}";
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
        }else if(!mCode.equals(serverPin)){
            Toast.makeText(RegisterActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
        }
        else {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("注册中，请稍后...");
            mDialog.show();
            registerNet();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!RegisterActivity.this.isFinishing()) {
                                mDialog.dismiss();
                            }
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
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
                }

            }).start();

    }
        }
    private void registerNet() {

    String httpUrl = "";
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
            case R.id.tv_message:
                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(messsage,30000,1000);
                countDownTimerUtils.onFinish();
                countDownTimerUtils.start();
                //同时调用接口获取验证码
                serverPin = "1234";
                break;
        }
    }
    /**
     * post请求后台
     * @param name
     * @param pwd
     */
    private void postRequest(String name,String pwd)  {
        //建立请求表单，添加上传服务器的参数
        //OkHttpUtils.post().addParams().addParams();
    }

}
