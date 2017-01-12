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
 * Created by DEll on 2016-12-19.
 * 转入的页面
 */
public class ChangeIntoActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private static final String TAG = "tag";
    private ImageView mBack;
    private View view;
    int count =3;
    private Button enterInto;
    private PopWindowView finishProjectPopupWindows;
    private FinishProjectPopupWindows1 finishProjectPopupWindows1;
    private CustomKeyboardView customKeyboardView;
    private TextView mDestory;
    private EditText money;
    private String mMoney;
    private String mMoney1;
    private TextView pwd;
    private TextView forgetPwd;
    private ImageView closed;
    private String uid;
    private int batch_id;
    private int prod_type_id;
    private String xgb1Money;
    private int xgb1Money1;
    private String mTime;
    private TextView intoDada;


    private String nowTime;


    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);

        enterInto = (Button) view.findViewById(R.id.btn_enter_change_into);

        mDestory = (TextView) view.findViewById(R.id.tv_destory);
        money = (EditText)view. findViewById(R.id.et_money);
        intoDada = (TextView) view.findViewById(R.id.tv_into_data);
    }

    @Override
    protected void initData()  {
        mBack.setOnClickListener(this);
        enterInto.setOnClickListener(this);
        mDestory.setOnClickListener(this);
        money.addTextChangedListener(this);
        mMoney =  enterInto.getText().toString().trim();
        GradientDrawable background = (GradientDrawable) enterInto.getBackground();
        background.setColor(Color.parseColor("#dadada"));
        enterInto.setEnabled(false);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        Log.i(TAG, "initData: "+ uid);
      new Thread(){
          @Override
          public void run() {
              try {
                 final String i = getSystemTime(1);
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          intoDada.setText(i);
                      }
                  });
                  System.out.println(i);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }.start();


    }

    @Override
    public View initView() {

        view = View.inflate(this, R.layout.activity_change_into, null);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_enter_change_into:

                xgb1Money1 = Integer.parseInt(money.getText().toString().trim());
                if (xgb1Money1 >100){
                Toast.makeText(ChangeIntoActivity.this, "金额不够请充值", Toast.LENGTH_SHORT).show();

                }else {
                    showPopWindow();
                }
                break;
            case R.id.tv_destory:
                Intent intent = new Intent(this,HoldingDetail1Activity.class);
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


        finishProjectPopupWindows.showAtLocation(this.findViewById(R.id.btn_enter_change_into),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        init();
    }

    private void init() {
        xgb1Money = money.getText().toString().trim();
        final View contentView = finishProjectPopupWindows.getContentView();
        forgetPwd = (TextView) contentView.findViewById(R.id.tv_forget_pwd);
        TextView tvMoney = (TextView) contentView.findViewById(R.id.tv_title_money);
        closed = (ImageView) contentView.findViewById(R.id.closed);
        forgetPwd.setOnClickListener(this);
        closed.setOnClickListener(this);
        tvMoney.setText("¥"+ xgb1Money);


        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
        customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView,
                new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                if ("123456".equals(text)){
                    //调用接口来转入

                    new Thread(){
                        @Override
                        public void run() {
                           postJson();
                        }
                    }.start();

                }else {

                    count--;

                    if (count > 0) {
                        finishProjectPopupWindows.dismiss();
                        finishProjectPopupWindows1 = new FinishProjectPopupWindows1(ChangeIntoActivity.this);
                        finishProjectPopupWindows1.showAtLocation(ChangeIntoActivity.this.findViewById(R.id.btn_enter_change_into),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        View contentView1 = finishProjectPopupWindows1.getContentView();
                        pwd = (TextView) contentView1.findViewById(R.id.tv_pwd);

                       pwd.setText("资金密码不正确,你还可用输入" + count + "次");
                        TextView mForgetPwd = (TextView) contentView1.findViewById(R.id.tv_forget_pwd);
                        mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finishProjectPopupWindows1.dismiss();
                                startActivity(new Intent(ChangeIntoActivity.this,ForgetPasswordActivity.class));
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


                    }


                    else {

                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    final String systemTime = getSystemTime(1);
                                    System.out.println(systemTime);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            MyApp.SP.edit().putString("data", systemTime).commit();
                                            MyApp.SP.edit().putString("pwd",uid).commit();
                                            Toast.makeText(ChangeIntoActivity.this, "密码超过3次,账号被锁定,请联系客服", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(ChangeIntoActivity.this,MainActivity.class));

                                        }
                                    });
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }
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
                "http://192.168.1.102:8080/api/orders/purchaseRMB",
                json);
        JSONObject j = null;
        try {
            j = new JSONObject(onlyJson);
            int result = j.getInt("rtcode");
            if (result==1){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    String startTime = getSystemTime(1);
                                    String stopTime = getSystemTime(2);
                                    MyApp.SP.edit().putString("startTime",startTime).commit();
                                    MyApp.SP.edit().putString("stopTime",stopTime).commit();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        Toast.makeText(ChangeIntoActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangeIntoActivity.this,ChangeIntoFinish.class);
                        intent.putExtra("money",xgb1Money);
                        startActivity(intent);
                        finish();
                    }
                });

            }else if (result==0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChangeIntoActivity.this, "交易失败,余额不够,请充值", Toast.LENGTH_SHORT).show();
                        finishProjectPopupWindows.dismiss();
                        finish();
                        //showPopWindow();
                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text1 = charSequence.toString().trim();
        Log.i(TAG, "beforeTextChanged: "+text1);
        if (text1.isEmpty()){
            // enterInto.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString().trim();
        GradientDrawable background;
        if (text.isEmpty()||text.startsWith("0")){
            background = (GradientDrawable) enterInto.getBackground();
            background.setColor(Color.parseColor("#dadada"));

            enterInto.setEnabled(false);
        }else {
            background = (GradientDrawable) enterInto.getBackground();
            background.setColor(Color.parseColor("#f39700"));
            enterInto.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

        }
    private String getSystemTime(final int i) throws IOException {

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
