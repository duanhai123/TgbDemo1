package com.geebit.app1.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.geebit.app1.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 历史保单的adapter
 */
public class RecylerDesInsuranceAdapter extends CommonAdapter {

    private TextView mTime;
    private TextView mUp;
    private TextView mUpFloat;

    public RecylerDesInsuranceAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }


    @Override
    protected void convert(ViewHolder holder, Object o, int position) {
        holder.setText(R.id.tv_time_destory, (String) o);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
