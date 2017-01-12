package com.geebit.app1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.bean.Products;
import com.geebit.app1.utils.CrmApiUtil;
import com.geebit.app1.utils.CustomKeyBoardUtil;
import com.geebit.app1.utils.CustomKeyboardView;
import com.geebit.app1.utils.FinishProjectPopupWindows1;
import com.geebit.app1.utils.PopWindowView;
import com.geebit.app1.view.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by DEll on 2016-12-21.
 * 转出的页面
 */
public class ChangeInoutActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private CustomKeyboardView customKeyboardView;
    private View view;
    private ImageView mBack;
    private Button mChangeOut;
    private EditText outMoney;
    int count =3;
    private PopWindowView finishProjectPopupWindows;
    private FinishProjectPopupWindows1 finishProjectPopupWindows1;
    private TextView mDestory;
    private TextView forgetPwd;
    private ImageView closed;
    private TextView title;
    private TextView titleMoney;
    private String uid;
    private int batch_id;
    private int prod_type_id;
    private String xgb1Money;
    private String nowTime;
    private float hold_amount;


    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mChangeOut = (Button) view.findViewById(R.id.btn_enter_change_inout);
        outMoney = (EditText) view.findViewById(R.id.et_money_inout);
        mDestory = (TextView) view.findViewById(R.id.tv_destory);

    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mChangeOut.setOnClickListener(this);
        mChangeOut.setEnabled(false);
        GradientDrawable background = (GradientDrawable) mChangeOut.getBackground();
        background.setColor(Color.parseColor("#dadada"));
        outMoney.addTextChangedListener(this);
        mDestory.setOnClickListener(this);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        hold_amount = MyApp.SP.getFloat("hold_amout", (float) 0.0);
        outMoney.setHint("可转出到可用"+ hold_amount);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_change_inout,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter_change_inout:
                String trim = outMoney.getText().toString().trim();
                int anInt = Integer.parseInt(trim);

                if (anInt>hold_amount){
                    Toast.makeText(ChangeInoutActivity.this, "金额不够请充值", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    showPopWindow();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_destory:
                Intent intent = new Intent(this,HoldingDetailActivity.class);
                intent.putExtra("changeinout","changeinout");
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                finish();
                break;
            case R.id.closed:
                finishProjectPopupWindows.dismiss();
                break;
            case R.id.custom_keyboard_view:
                finishProjectPopupWindows.dismiss();
                break;
        }
    }

    private void showPopWindow() {
        finishProjectPopupWindows = new PopWindowView(R.layout.input_pwd,this);
        finishProjectPopupWindows.showAtLocation(this.findViewById(R.id.btn_enter_change_inout),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        init();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString().trim();
        if (text.isEmpty()||text.startsWith("0")){
            GradientDrawable background = (GradientDrawable) mChangeOut.getBackground();
            background.setColor(Color.parseColor("#dadada"));
            mChangeOut.setEnabled(false);
        }else {
            GradientDrawable background = (GradientDrawable) mChangeOut.getBackground();
            background.setColor(Color.parseColor("#f39700"));
            mChangeOut.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    private void init() {
        xgb1Money = outMoney.getText().toString().trim();
        final View contentView = finishProjectPopupWindows.getContentView();
        forgetPwd = (TextView) contentView.findViewById(R.id.tv_forget_pwd);
        closed = (ImageView) contentView.findViewById(R.id.closed);
        TextView tvMoney = (TextView) contentView.findViewById(R.id.tv_title_money);
        title = (TextView) contentView.findViewById(R.id.tv_title);
        titleMoney = (TextView) contentView.findViewById(R.id.tv_title_money);
        closed.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        title.setText("转出");
        tvMoney.setText("¥"+ xgb1Money);
        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
        customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this,
                linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                if ("123456".equals(text)){
                    new Thread(){
                        @Override
                        public void run() {
                            postJson();
                        }
                    }.start();
                }else {
                    count--;

                    if (count>0){
                        finishProjectPopupWindows.dismiss();
                        finishProjectPopupWindows1 = new FinishProjectPopupWindows1(ChangeInoutActivity.this);


                        finishProjectPopupWindows1.showAtLocation(ChangeInoutActivity.
                                this.findViewById(R.id.btn_enter_change_inout),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                        View contentView1 = finishProjectPopupWindows1.getContentView();
                        TextView mPwd = (TextView) contentView1.findViewById(R.id.tv_pwd);
                        mPwd.setText("资金密码不正确,你还可用输入" + count + "次");
                        TextView mForgetPwd = (TextView) contentView1.findViewById(R.id.tv_forget_pwd);
                        mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();
                                startActivity(new Intent(ChangeInoutActivity.this,ForgetPasswordActivity.class));
                                //finish();
                            }
                        });
                        TextView mAgain = (TextView) contentView1.findViewById(R.id.tv_again);
                        mAgain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();

                                showPopWindow();
                            }
                        });
                    }else {

                        new Thread(){
                            @Override
                            public void run() {
                                final String systemTime = getSystemTime(1);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MyApp.SP.edit().putString("data", systemTime).commit();
                                        MyApp.SP.edit().putString("pwd",uid).commit();
                                        Toast.makeText(ChangeInoutActivity.this, "密码超过3次,账号被锁定,请明天再试",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ChangeInoutActivity.this,MainActivity.class));

                                    }
                                });
                            }
                        }.start();
                    }
                }
            }
        });
    }
    private void postJson() {
        HashMap hashMap = new HashMap();
        hashMap.put("user_id",uid);
        JSONObject jsonObject = new JSONObject(hashMap);
        String json = jsonObject.toString();
        String onlyJson = CrmApiUtil.postOnlyJson("http://192.168.1.102:8080/api/orders/products", json);
        //解析数据吧id保存
        Gson gson = new Gson();
        Type type = new TypeToken<Products>(){}.getType();
        Products products =  gson.fromJson(onlyJson,type);
        batch_id = products.getData().get(0).getBatch_id();
        prod_type_id = products.getData().get(0).getProd_type_id();
        postJsonInto();
    }
    private void postJsonInto() {

        HashMap hashMap = new HashMap();
        hashMap.put("user_id",uid);
        hashMap.put("batch_id",batch_id);
        hashMap.put("prod_type_id",prod_type_id);
        hashMap.put("apply_amount",xgb1Money);
        JSONObject jsonObject = new JSONObject(hashMap);
        String json = jsonObject.toString();
        String onlyJson = CrmApiUtil.postOnlyJson(
                "http://192.168.1.102:8080/api/orders/redemptionRMB",
                json);
        JSONObject j = null;
        try {
            j = new JSONObject(onlyJson);
            int result = j.getInt("rtcode");
            if (result==1){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChangeInoutActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangeInoutActivity.this,ResultDetailActivity.class);
                        intent.putExtra("money",xgb1Money);
                        startActivity(intent);
                        finish();
                    }
                });

            }else if (result==0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChangeInoutActivity.this, "交易失败,余额不够,请转入金额", Toast.LENGTH_SHORT).show();
                        finish();
                        finishProjectPopupWindows.dismiss();
                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private String getSystemTime(int i){
        try {
            URL url = new URL("http://open.baidu.com/special/time/");
            URLConnection uc = url.openConnection();
            uc.connect();
            long id = uc.getDate();
            Date date = new Date(id);
            Calendar calendar   =   new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
            date=calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat("MM-dd EEEE");
            nowTime = format.format(date);

            Log.i("时间", date.getHours() + "时" + date.getMinutes() + "分"
                    + date.getSeconds() + "秒" + "\n" + nowTime);
            // testDate.setText(nowTime);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return nowTime ;
    }
}
