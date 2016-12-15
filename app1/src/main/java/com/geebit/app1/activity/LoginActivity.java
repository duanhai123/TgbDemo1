package com.geebit.app1.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Pattern;

import okhttp3.Call;


/**
 * Created by admin on 2016/11/26.
 */
public class LoginActivity extends Activity {
    private static final String TAG ="tag" ;
    // 弹出框
    private ProgressDialog mDialog;

    // username 输入框
    private EditText mUsernameEdit;
    // 密码输入框
    private EditText mPasswordEdit;

    // 注册按钮
    private TextView mSignUpBtn;
    // 登录按钮
    private Button mSignInBtn;
    //忘记密码按键
    private TextView mForgetPassword;
    private String username;
    private String password;
    private CheckBox cb_agree;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {

        mUsernameEdit = (EditText) findViewById(R.id.ec_edit_username);
        mPasswordEdit = (EditText) findViewById(R.id.ec_edit_password);
        mForgetPassword = (TextView) findViewById(R.id.ec_edit_forget_password);
        mSignUpBtn = (TextView) findViewById(R.id.ec_btn_sign_up);
        cb_agree = (CheckBox) findViewById(R.id.cb_agree);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signUp();
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });

        mSignInBtn = (Button) findViewById(R.id.ec_btn_sign_in);
        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));

            }
        });
    }



    /**
     * 登录方法
     */
    private void login(){
        /*POST请求 OkHttpUtils.post().url(url).addParams("username", "hyman").addParams("password", "123")
                .build().execute(callback);
        Post JSON OkHttpUtils.postString().url(url).content(new Gson().toJson(new User("zhy", "123")))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new MyStringCallback());*/
        String httpUrl="http://www.csdn.net/";
       OkHttpUtils.post().url(httpUrl).addParams("username", "hyman").addParams("password", "123")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //请求失败
                Log.i(TAG, "onError: "+e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "网络超时", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(String response, int id) {
                //请求成功
                Log.i(TAG, "onResponse: "+response);
                mDialog.dismiss();
            }
        });


    }


    private void signIn() {
        username = mUsernameEdit.getText().toString().trim();
        password = mPasswordEdit.getText().toString().trim();


        String num = "[1][358]\\d{9}";
        Pattern pattern = Pattern.compile("[0-9]*");
        boolean matches = username.matches(num);

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "用户或者密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if (!cb_agree.isChecked()){
            Toast.makeText(LoginActivity.this, "请勾选我已阅读并且同意服务条款", Toast.LENGTH_SHORT).show();
            return;
        }else if (!matches){
            Toast.makeText(LoginActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }else if (password.length()<6||pattern.matcher(password.substring(0,1)).matches()){
            Toast.makeText(LoginActivity.this, "密码以字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("正在登陆，请稍后...");
            mDialog.show();
            login();

        }
    }
}
