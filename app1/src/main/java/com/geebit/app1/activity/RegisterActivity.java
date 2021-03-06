package com.geebit.app1.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.bean.RegisterUser;
import com.geebit.app1.bean.RtcodeCommCode;
import com.geebit.app1.utils.CountDownTimerUtils;
import com.geebit.app1.utils.CrmApiUtil;
import com.geebit.app1.view.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
    private String onlyJson;
    private String recommendCode;
    private int uid;


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
        new Thread(){
            @Override
            public void run() {

                getUserCode(uid+"");
            }
        }.start();
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


        if (username.isEmpty() || password.isEmpty() || mEnterPwder.isEmpty() || pwdPin.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "账号和登录密码密码或者短信验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if (!(TextUtils.isEmpty(mCode))&&(!recommendCode.equals(mCode))){
            Toast.makeText(RegisterActivity.this, "推荐码不存在,如果没有就不要输入", Toast.LENGTH_SHORT).show();
            return;
        } else if (!(password.equals(mEnterPwder))) {
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致请重新输入", Toast.LENGTH_SHORT).show();
            return;
        } else if (!matches) {
            Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.length() < 6 || pattern.matcher(password.substring(0, 1)).matches()) {
            Toast.makeText(RegisterActivity.this, "密码以字母开头，长度在6~18之间，只能包含字符、数字和下划线", Toast.LENGTH_SHORT).show();
            return;
        } else if (!cbAgree.isChecked()) {
            Toast.makeText(RegisterActivity.this, "请勾选我已阅读并且同意服务条款", Toast.LENGTH_SHORT).show();
        } else if (!(pwdPin.equals("123456"))) {
            Toast.makeText(RegisterActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
        } else {
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

                }

            }).start();
            new Thread() {
                @Override
                public void run() {
                    postRequest(username, password);

                }
            }.start();

            }
        }

    //调用后台获取推荐码
    private void getUserCode(String user_id) {
        String url = "http://192.168.1.102:8080/api/user/addCommenderCode";
        String code = CrmApiUtil.postMap(user_id);
        String UserCode = CrmApiUtil.postOnlyJson(url, code);
        try {
            JSONObject j = new JSONObject(UserCode);
            int rtcode = j.getInt("rtcode");
            if (rtcode==0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(RegisterActivity.this, "推荐码不存在", Toast.LENGTH_SHORT).show();
                    }
                });

            }else {
                //存在的,去解析数据拿到推荐码和用户做比较
                Gson gson = new Gson();
                Type type = new TypeToken<RtcodeCommCode>(){}.getType();
               RtcodeCommCode commCode =  gson.fromJson(UserCode,type);
                recommendCode = commCode.getData().getRecommend_code();

            }

        } catch (JSONException e) {
            e.printStackTrace();
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

                            postRequest(username);

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
    private void postRequest( String tell, String pwd)  {

        //建立请求表单，添加上传服务器的参数
        String url = "http://192.168.1.102:8080/api/user/register";
        String md5Pwd = CrmApiUtil.getMd5(pwd);
         s =  CrmApiUtil.postMap(tell, md5Pwd);
       onlyJson = CrmApiUtil.postOnlyJson(url, s);
        Log.i(TAG, "run: "+onlyJson);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {
                    if (onlyJson!=null) {
                      JSONObject  js = new JSONObject(onlyJson);

                        int rtcode = js.getInt("rtcode");
                        Log.i(TAG, "register: " + rtcode);
                        if (rtcode == 1) {
                            MyApp.SP.edit().putBoolean("user",true).commit();
                            //解析数据吧id保存
                            Gson gson = new Gson();
                            Type type = new TypeToken<RegisterUser>(){}.getType();
                            RegisterUser registerUser =  gson.fromJson(onlyJson,type);
                            uid = registerUser.getData().getUid();
                            MyApp.SP.edit().putString("uid", uid +"").commit();
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            finish();


                        } else if (rtcode==2){

                            Toast.makeText(RegisterActivity.this, "注册失败,账号已经存在", Toast.LENGTH_SHORT).show();
                            return;



                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });






    }
    //请求短信注册
    private void postRequest(String tell){

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
