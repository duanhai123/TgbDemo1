package com.geebit.app1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geebit.app1.R;
import com.geebit.app1.exception.OnDrawingException;
import com.geebit.app1.utils.CustomKeyBoardUtil;
import com.geebit.app1.utils.CustomKeyboardView;
import com.geebit.app1.utils.PopWindowUtils;
import com.geebit.app1.utils.PopWindowView;
import com.geebit.app1.view.RingView;

/*author: 段海鹏
 * data: 2017.1.3
 * 借币还币的页面
 */
public class BorrowMoneyActivity extends BaseActivity implements View.OnClickListener, TextWatcher, SeekBar.OnSeekBarChangeListener {
    private int count =3;
    private View view;
    private TextView isMoney;
    private TextView borrowMoney;
    private LinearLayout llIsMoney;
    private LinearLayout llBorrowMoney;
    private ImageView mBack;
    private RingView ring;
    private TextView destoryBorrow;
    private Button agreeBorrow;
    private EditText mBorrow;
    private CheckBox cbBorrow;
    private LinearLayout mInterest;
    private PopWindowView inputPwdpp;
    private PopupWindow p;
    private SeekBar seekbar;
    private TextView mPopop;
    private GradientDrawable drawable;
    private GradientDrawable drawable1;


    @Override
    protected void initoView() {
        isMoney = (TextView) view.findViewById(R.id.tv_is_money);
        borrowMoney = (TextView) view.findViewById(R.id.tv_borrow_money);
        llIsMoney = (LinearLayout) findViewById(R.id.ll_is_money);
        llBorrowMoney = (LinearLayout) findViewById(R.id.ll_borrow_money);
        ring = (RingView) findViewById(R.id.rv);
        mBack = (ImageView) findViewById(R.id.iv_back);
        destoryBorrow = (TextView) findViewById(R.id.tv_destory_borrow);
        agreeBorrow = (Button) findViewById(R.id.btn_agree_borrow);
        mBorrow = (EditText) findViewById(R.id.et_borrow);
        cbBorrow = (CheckBox) findViewById(R.id.cb_borrow);
        mInterest = (LinearLayout) findViewById(R.id.ll_interest);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        mPopop = (TextView) findViewById(R.id.tv_popop);

    }

    private void setSelector() {
        agreeBorrow.setBackgroundColor(Color.parseColor("#dadada"));
        agreeBorrow.setEnabled(false);
    }

    @Override
    protected void initData() {
        isMoney.setOnClickListener(this);
        borrowMoney.setOnClickListener(this);
       // llBorrowMoney.setVisibility(View.VISIBLE);
        //llIsMoney.setVisibility(View.INVISIBLE);
        mBack.setOnClickListener(this);
        agreeBorrow.setOnClickListener(this);
        mBorrow.addTextChangedListener(this);
        mInterest.setOnClickListener(this);
        destoryBorrow.setOnClickListener(this);
        seekbar.setOnSeekBarChangeListener(this);
        setStartDraw();
        setSelector();
        setShapeColor();
    }
    //设置环型的颜色和百分比
    private void setStartDraw() {
        ring.setColors(new int[]{Color.parseColor("#dadada")

                ,Color.parseColor("#faa755"),Color.GRAY});
        ring.setValues(new int[]{65, 20,15});
        try {
            ring.startDraw();
        } catch (OnDrawingException e) {
            e.printStackTrace();
        }
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
                setShapeColor1();
                break;
            case R.id.tv_borrow_money:
                llBorrowMoney.setVisibility(View.VISIBLE);
                llIsMoney.setVisibility(View.INVISIBLE);
                setShapeColor();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_destory_borrow:
                Intent intent = new Intent(this,TwoHoldingDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_agree_borrow:
                if (!cbBorrow.isChecked()){
                    Toast.makeText(BorrowMoneyActivity.this, "请勾选本人阅读并且同意签署", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    showPwdpp();
                }
                break;
            case R.id.ll_interest:
                Intent intent1 = new Intent(this,IntersterItemActivity.class);
                startActivity(intent1);
                //finish();
                break;

        }
    }

    private void setShapeColor1() {
        drawable = (GradientDrawable) borrowMoney.getBackground();
        drawable.setColor(Color.TRANSPARENT);
        drawable1 = (GradientDrawable) isMoney.getBackground();
        drawable1.setColor(Color.parseColor("#f39700"));
    }

    private void setShapeColor() {
        drawable = (GradientDrawable) borrowMoney.getBackground();
        drawable.setColor(Color.parseColor("#f39700"));
        drawable1 = (GradientDrawable) isMoney.getBackground();
        drawable1.setColor(Color.TRANSPARENT);
    }

    private void showPwdpp() {
        inputPwdpp = new PopWindowView(R.layout.two_input_pwd,this);
        inputPwdpp.showAtLocation(this.findViewById(R.id.btn_agree_borrow),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0
        );
        View contentView = inputPwdpp.getContentView();
        TextView tv_title = (TextView) contentView.findViewById(R.id.tv_title);
        tv_title.setText("借币");
        init();
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString().trim();
        if (TextUtils.isEmpty(text)||text.startsWith("0")){
            setSelector();
            return;
        }else {
            agreeBorrow.setBackgroundColor(Color.parseColor("#f39700"));
            agreeBorrow.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    protected void init() {

        final View contentView = inputPwdpp.getContentView();
       TextView forgetPwd = (TextView) contentView.findViewById(R.id.tv_forget_pwd);
        ImageView closed = (ImageView) contentView.findViewById(R.id.iv_closed);
        forgetPwd.setOnClickListener(this);
        closed.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout)contentView. findViewById(R.id.layout_input);
        CustomKeyboardView customKeyboardView = (CustomKeyboardView)contentView. findViewById(R.id.custom_keyboard_view);

        CustomKeyBoardUtil customKeyBoardUtil = new CustomKeyBoardUtil(this, linearLayout, customKeyboardView, new CustomKeyBoardUtil.InputFinishListener() {
            @Override
            public void inputHasOver(String text) {
                if ("123456".equals(text)){
                    Toast.makeText(BorrowMoneyActivity.this, "交易成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BorrowMoneyActivity.this,BorrowMoneyFinishActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    count--;

                    if (count > 0) {
                        inputPwdpp.dismiss();

                       p =  PopWindowUtils.popWindow(R.style.Animation,BorrowMoneyActivity.this,
                                R.layout.input_forget,BorrowMoneyActivity.this,0,0,R.id.btn_agree_borrow);
                        View contentView1 = p.getContentView();
                       TextView pwd = (TextView) contentView1.findViewById(R.id.tv_pwd);

                        pwd.setText("资金密码不正确,你还可用输入" + count + "次");
                        TextView mForgetPwd = (TextView) contentView1.findViewById(R.id.tv_forget_pwd);

                        mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                p.dismiss();
                                startActivity(new Intent(BorrowMoneyActivity.this,ForgetPasswordActivity.class));
                                //finish();
                            }
                        });
                        TextView mAgain = (TextView) contentView1.findViewById(R.id.tv_again);
                        mAgain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                p.dismiss();

                                showPwdpp();
                            }
                        });


                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BorrowMoneyActivity.this,
                                        "密码超过3次,账号被锁定,请联系客服",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(BorrowMoneyActivity.
                                        this,MainActivity.class));

                            }
                        });

                    }

                }


            }
        });

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //该方法拖动进度条进度改变的时候调用
        mPopop.setText("已借"+i+".0000฿");
        int position = seekBar.getProgress()+40; //seekbar当前进度
        float x = seekBar.getWidth();//seekbar的当前位置
        float seekbarWidth = seekBar.getX() ; //seekbar的宽度
        float width = (position*x)/100+seekbarWidth; //seekbar当前位置的宽度
        mPopop.setX(width);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //该方法拖动进度条开始拖动的时候调用
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
       // 该方法拖动进度条停止拖动的时候调用

    }
}
