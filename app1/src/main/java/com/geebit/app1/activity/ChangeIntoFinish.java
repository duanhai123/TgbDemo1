package com.geebit.app1.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.view.MyApp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by DEll on 2016-12-23.
 * 转入完成的页面
 */
public class ChangeIntoFinish extends BaseActivity implements View.OnClickListener {
    private String mTime;
    private View view;
    private TextView mFinish;
    private ImageView mBack;
    private TextView intoMoney;
    private TextView monStop;
    private TextView monStart;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mFinish = (TextView) view.findViewById(R.id.tv_finish);
        intoMoney = (TextView)view. findViewById(R.id.tv_money_top);
        monStart = (TextView)view .findViewById(R.id.tv_mon_start);
        monStop = (TextView) view.findViewById(R.id.tv_mon_stop);
    }

    @Override
    protected void initData() {
        mBack.setOnClickListener(this);
        mFinish.setOnClickListener(this);
        Intent intent = getIntent();
        final String money = intent.getStringExtra("money");

        Intent intent1 =getIntent();
        String getMoney = intent.getStringExtra("getmoney");
        if (getMoney!=null){
            getMoney(getMoney);
        }else {
            getMoney(money);
        }
    }

    private void getMoney(final String money) {
        new Thread(){
            @Override
            public void run() {
                final String dada = getDada(0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String startTime = MyApp.SP.getString("startTime", "");
                        String stopTime = MyApp.SP.getString("stopTime", "");

                        if (dada.equals(startTime)) {
                            Toast.makeText(ChangeIntoFinish.this, "11", Toast.LENGTH_SHORT).show();
                            intoMoney.setText("成功转入" + money + "元");
                            monStart.setText("今天");
                            monStop.setText(stopTime);
                        } else if (dada.equals(stopTime)) {
                            Toast.makeText(ChangeIntoFinish.this, "22", Toast.LENGTH_SHORT).show();
                            intoMoney.setText("成功转入" + money + "元");
                            monStart.setText( startTime);
                            monStop.setText("收益到账");
                        } else {
                            MyApp.SP.edit().putString("into",money).commit();
                            intoMoney.setText("成功转入" + money + "元");
                            monStart.setText(startTime + "");
                            monStop.setText(stopTime + "");
                        }
                    }
                });
            }
        }.start();
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_changeinto_finish,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }

    String nowTime;
    private String getDada(int i){
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
