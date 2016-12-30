package com.geebit.app1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.utils.CrmApiUtil;
import com.geebit.app1.view.MyApp;
import com.squareup.okhttp.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * Created by admin on 2016/11/26.
 */
public class LoginActivity extends BaseActivity {
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
    private static final String TAG ="tags" ;
    // 弹出框
    private ProgressDialog mDialog;

    // username 输入框
    private EditText mUsernameEdit;
    // 密码输入框
    private EditText mPasswordEdit;

    // 注册按钮
    private TextView mRegister;
    // 登录按钮
    private Button mSignInBtn;
    //忘记密码按键
    private TextView mForgetPassword;

    private String username;
    private String password;
    private CheckBox cb_agree;
    private SharedPreferences sp;
    private View view;
    private String s;



    @Override
    protected void initoView() {
        mUsernameEdit = (EditText)view. findViewById(R.id.ec_edit_username);
        mPasswordEdit = (EditText) view.findViewById(R.id.ec_edit_password);
        mForgetPassword = (TextView)view. findViewById(R.id.ec_edit_forget_password);
        mRegister = (TextView) view.findViewById(R.id.ec_btn_register);
        cb_agree = (CheckBox) view.findViewById(R.id.cb_agree);

    }

    @Override
    protected void initData() {
        mRegister.setOnClickListener(new View.OnClickListener() {
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

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(2000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
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
     * 初始化界面控件
     */
    public View initView() {
            


        view = View.inflate(this, R.layout.activity_login,null);
        return view;
    }



    /**
     * 登录方法
     */
    private void login(String tel,String pwd){

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
            new Thread(){

                @Override
                public void run() {
                    postJson();
                    Log.i(TAG, "run: "+ s);
                }
            }.start();


        }
    }

    private void postJson() {
        HashMap map = new HashMap();
        map.put("tel",username);
        map.put("password",password);
        JSONObject jsonObject = new JSONObject(map);
        String json = jsonObject.toString();

        s = CrmApiUtil.postOnlyJson("http://192.168.1.102:8080/api/user/login", json);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "signIn: "+s);
                    if (s!=null) {

                        JSONObject j = new JSONObject(s);
                        int result = j.getInt("rtcode");
                        if (result==1) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            MyApp.SP.edit().putBoolean("user",true).commit();
                        } else if (result==0) {
                            Toast.makeText(LoginActivity.this, "登录失败,账号密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
