package com.geebit.app1.activity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.geebit.app1.R;
import com.geebit.app1.utils.FinishProjectPopupWindows;

/**
 * Created by DEll on 2016-12-19.
 */
public class ChangeIntoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private View view;
    private LinearLayout inputPwd;
    private Button enterInto;
    private PopupWindow popupWindow;
    private FinishProjectPopupWindows finishProjectPopupWindows;


    @Override
    protected void initoView() {
         mBack = (ImageView)view. findViewById(R.id.iv_back);

        enterInto = (Button)view. findViewById(R.id.btn_enter_change_into);

    }

    @Override
    protected void initData() {

        mBack.setOnClickListener(this);
        enterInto.setOnClickListener(this);
    }

    @Override
    public View initView() {

        view = View.inflate(this, R.layout.activity_change_into,null);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_enter_change_into:
                finishProjectPopupWindows = new FinishProjectPopupWindows(this,itemsOnClick);
                finishProjectPopupWindows.showAtLocation(this.findViewById(R.id.btn_enter_change_into),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }

    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.closed:
                    finishProjectPopupWindows.dismiss();
                    break;
            }
        }
    };

}
