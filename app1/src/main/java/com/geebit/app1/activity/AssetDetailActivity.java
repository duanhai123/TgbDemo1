package com.geebit.app1.activity;/* data: 2017-01-09
 * author: 段海鹏
 * ui: 资产操作明细
 */

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geebit.app1.R;

public class AssetDetailActivity extends BaseActivity implements View.OnClickListener {

    private View view;
    private ImageView mBack;
    private TextView tvAsset;
    private TextView tvAssetNone;
    private LinearLayout llAsset;
    private LinearLayout llAssetNone;
    private LinearLayout assetImage;
    private LinearLayout noAssetImage;

    @Override
    protected void initoView() {
        mBack = (ImageView) view.findViewById(R.id.iv_back_detail);
        tvAsset = (TextView) view.findViewById(R.id.tv_asset_text);
        tvAssetNone = (TextView) view.findViewById(R.id.tv_asset_none_text);
        llAsset = (LinearLayout) view.findViewById(R.id.ll_asset_line);
        llAssetNone = (LinearLayout) view.findViewById(R.id.ll_none_asset_line);
        assetImage = (LinearLayout) view.findViewById(R.id.ll_asset_image);
        noAssetImage = (LinearLayout) view.findViewById(R.id.ll_none_asset_image);

    }

    @Override
    protected void initData() {
        tvAsset.setOnClickListener(this);
        tvAssetNone.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_asset_detail,null);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_detail:
                finish();
                break;
            case R.id.tv_asset_text:
                tvAsset.setTextColor(Color.parseColor("#f39700"));
                tvAssetNone.setTextColor(Color.parseColor("#656565"));
                llAsset.setVisibility(View.VISIBLE);
                llAssetNone.setVisibility(View.INVISIBLE);
                assetImage.setVisibility(View.VISIBLE);
                noAssetImage.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_asset_none_text:
                tvAsset.setTextColor(Color.parseColor("#656565"));
                tvAssetNone.setTextColor(Color.parseColor("#f39700"));
                llAsset.setVisibility(View.INVISIBLE);
                llAssetNone.setVisibility(View.VISIBLE);
                assetImage.setVisibility(View.INVISIBLE);
                noAssetImage.setVisibility(View.VISIBLE);
                break;
        }
    }
}
