package com.geebit.app1.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by DEll on 2016-12-23.
 */
public class RecylerAdapter extends CommonAdapter {

    private TextView mTime;
    private TextView mUp;
    private TextView mUpFloat;

    public RecylerAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }


    @Override
    protected void convert(ViewHolder holder, Object o, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
