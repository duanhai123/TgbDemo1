package com.geebit.app1.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.geebit.app1.R;
import com.geebit.app1.bean.Products;
import com.geebit.app1.fragment.FragmentMe;
import com.geebit.app1.fragment.FragmentWtgb;
import com.geebit.app1.fragment.FragmentWxgbOne;
import com.geebit.app1.fragment.FragmentWxgbTwo;
import com.geebit.app1.listener.ScreenListener;
import com.geebit.app1.utils.CrmApiUtil;
import com.geebit.app1.utils.NetworkUtils;
import com.geebit.app1.utils.PrefUtils;
import com.geebit.app1.view.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by admin on 2016/12/17.
 */
public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private static final String TAG ="tag" ;
    //百度推送key
    private String APP_Key = "VS3maAGhd8Hi2Lg0SQMy65K66HbApmoZ";
    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    private long exitTime = 0;
    // 定义一个布局
    private LayoutInflater layoutInflater;
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = {FragmentWtgb.class,
            FragmentWxgbOne.class,
            FragmentWxgbTwo.class, FragmentMe.class};

    //Tab选项卡的图片
    private int imageResources[] = {R.drawable.selector_icon_wtgb, R.drawable.selector_icon_wxgb1,
            R.drawable.selector_icon_wxgb2, R.drawable.selector_icon_user};
    private int batch_id;
    private int prod_type_id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;
    boolean connByHttp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean user = MyApp.SP.getBoolean("user", false);


        if (user) {

        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        boolean network = isNetwork();

        new Thread(){
            @Override
            public void run() {
                 connByHttp = isConnByHttp();
                System.out.println(connByHttp);
            }
        }.start();
        if (network ) {
            initView();
            new Thread() {
                @Override
                public void run() {
                    postJson();
                }
            }.start();
        }else if (!connByHttp){
            checkNetwork(this);

        }



           // ScreenListen();
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        Log.i(TAG, "onCreate: "+uid);

    }




    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //初始化百度推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,APP_Key);
        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec =  mTabHost.newTabSpec(imageResources[i]+"")
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.black);
            //去除分割线
            mTabHost.getTabWidget().setDividerDrawable(null);
            mTabHost.setCurrentTab(1);
            //更换背景图片
            mTabHost.setOnTabChangedListener(this);

        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_main);
        imageView.setImageResource(imageResources[index]);

        return view;
    }


    @Override
    public void onTabChanged(String s) {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
                PrefUtils.remove("user",MyApp.application);
                finish();
               System.exit(0);

            return true;
        }
        return super.onKeyDown(keyCode, event);

    }



    private void ScreenListen(){

        ScreenListener i = new ScreenListener(this);
        i.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Log.i(TAG, "onScreenOn:开屏 ");

            }

            @Override
            public void onScreenOff() {
                Log.i(TAG, "onScreenOff: 锁屏");
                PrefUtils.remove("user",MyApp.application);
            }

            @Override
            public void onUserPresent() {
                Log.i(TAG, "onUserPresent: 解锁");
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
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
        MyApp.SP.edit().putString("batch_id",batch_id+"").commit();
        MyApp.SP.edit().putString("prod_type_id",prod_type_id+"").commit();
    }
    //判断是否有网络
    private boolean isNetwork(){
        boolean isNetwork = NetworkUtils.isNetworkAvalible(this);
        boolean isNetstate = NetworkUtils.netState(this);
        if (isNetwork&&isNetstate){
            return true;
        }else if (isNetwork==false){
            NetworkUtils.checkNetwork(this);
            return false;
        }else if (isNetwork&&isNetstate==false){
            Toast.makeText(this, "网路连接不可用,请查看", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean isConnByHttp() {
        boolean isConn = false;
        URL url;
        HttpURLConnection conn = null;

        try {
            url = new URL("http://192.168.1.102:8080/api/orders/incomeStatement");
            conn = (HttpURLConnection) url.openConnection();
            // conn.setHeader("Range","bytes="+"");
            conn.setConnectTimeout(1000 * 8);
            if (conn.getResponseCode() == 200) {
                isConn = true;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block

        } catch (IOException e) {
            // TODO Auto-generated catch block

        } finally {
            conn.disconnect();
        }
        return isConn;
    }
    // 如果没有连接到服务器，则弹出对话框
    public  void checkNetwork(final Activity activity) {

            TextView msg = new TextView(activity);
            msg.setText("当前服务器连接不上,请联系客服");
            new AlertDialog.Builder(activity).setIcon(R.drawable.icon_1).setTitle("网络状态提示")
                    .setView(msg).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    // 跳转到设置界面
                   // activity.startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS), 0);
                    System.exit(0);
                }
            }).create().show();

        return;
    }
}
