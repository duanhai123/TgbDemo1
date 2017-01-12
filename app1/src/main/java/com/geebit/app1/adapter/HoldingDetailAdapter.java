package com.geebit.app1.adapter;/* data: 2017-01-12
 * author: 段海鹏
 * ui: 
 */

import android.content.Context;

import com.geebit.app1.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class HoldingDetailAdapter extends CommonAdapter {
    public HoldingDetailAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Object o, int position) {
        holder.setText(R.id.tv_time,o.toString());
    }
}
