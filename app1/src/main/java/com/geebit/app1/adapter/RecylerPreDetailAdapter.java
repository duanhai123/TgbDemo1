package com.geebit.app1.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 历史保单的adapter
 */
public class RecylerPreDetailAdapter extends CommonAdapter {
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private TextView mTime;
    private TextView mUp;
    private TextView mUpFloat;

    public RecylerPreDetailAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }


    @Override
    protected void convert(ViewHolder holder, Object o, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


}
