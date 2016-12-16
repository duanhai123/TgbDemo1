package com.geebit.app1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.geebit.app1.R;
import com.geebit.app1.fragment.FragmentCategory;
import com.geebit.app1.fragment.FragmentHome;
import com.geebit.app1.fragment.FragmentSetting;
import com.geebit.app1.fragment.FragmentUser;

/**
 * 
 * @author yechao
 * @功能说明 自定义TabHost
 *
 */
public class MainActivity extends FragmentActivity {
	//百度推送key
	private String APP_Key = "VS3maAGhd8Hi2Lg0SQMy65K66HbApmoZ";
	private static final String TAG = "tag";
	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;

	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { FragmentUser.class,
			FragmentCategory.class,
			FragmentSetting.class ,FragmentHome.class};




	//Tab选项卡的图片
	private int imageResources[] ={R.drawable.wtgb_1,R.drawable.wxgb_1b,
			R.drawable.wxgb_2,R.drawable.me_1};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		initView();
	}

	/**
	 * 初始化组件
	 */
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
			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(imageResources[i]+"")
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.color.black);
			mTabHost.setCurrentTab(1);
             if (i==0) {
				 mTabHost.getTabWidget().getChildAt(i).setOnClickListener(new View.OnClickListener() {
					 @Override
					 public void onClick(View view) {

							 startActivity(new Intent(MainActivity.this, LoginActivity.class));
							 finish();

					 }
				 });
			 }
			}
		}



	/**
	* 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		//ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		//imageView.setImageResource(mImageViewArray[index]);

		ImageView imageView = (ImageView) view.findViewById(R.id.iv_main);
		imageView.setImageResource(imageResources[index]);

		return view;
	}


}
