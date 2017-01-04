package com.geebit.app1.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geebit.app1.R;

/*author: 段海鹏
 * data: 2017.1.3
 * 借币还币的页面
 */
public class BorrowMoneyActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private TextView isMoney;
    private TextView borrowMoney;
    private LinearLayout llIsMoney;
    private LinearLayout llBorrowMoney;
    private ImageView mBack;



    @Override
    protected void initoView() {
        isMoney = (TextView) view.findViewById(R.id.tv_is_money);
        borrowMoney = (TextView) view.findViewById(R.id.tv_borrow_money);
        llIsMoney = (LinearLayout) findViewById(R.id.ll_is_money);
        llBorrowMoney = (LinearLayout) findViewById(R.id.ll_borrow_money);
        mBack = (ImageView) findViewById(R.id.iv_back);

    }

    @Override
    protected void initData() {
        isMoney.setOnClickListener(this);
        borrowMoney.setOnClickListener(this);
       // llBorrowMoney.setVisibility(View.VISIBLE);
        //llIsMoney.setVisibility(View.INVISIBLE);
        mBack.setOnClickListener(this);

    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_borrow_money,null);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_is_money:
                llBorrowMoney.setVisibility(View.INVISIBLE);
                llIsMoney.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_borrow_money:
                llBorrowMoney.setVisibility(View.VISIBLE);
                llIsMoney.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
