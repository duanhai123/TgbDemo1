package com.geebit.app1.activity;

import android.view.View;

import com.geebit.app1.R;

/*author: 段海鹏
 * data: 2017.1.3
 * 借币的页面
 */
public class BorrowMoneyActivity extends BaseActivity {

    private View view;

    @Override
    protected void initoView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_borrow_money,null);
        return view;
    }
}
