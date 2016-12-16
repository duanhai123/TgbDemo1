package com.geebit.app1.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geebit.app1.R;


public class FragmentCategory extends BaseFragment {


	private View view;




	@Override
	public View initView() {
		view = View.inflate(mActivity, R.layout.fragment_category,null);


		return view;
	}

	@Override
	public void initData() {



	}



	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(mActivity);
			textView.setText(1+"");
			textView.setTextSize(100);
			return textView;
		}
	}

}
