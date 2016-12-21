package com.geebit.app1.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geebit.app1.R;
import com.geebit.app1.activity.DividendDetailActivity;

import java.util.List;

/**
 * Created by DEll on 2016-12-21.
 */
public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.RecylerViewHodle> {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public RecylerAdapter( Context mContext,List<String> mDatas) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public RecylerAdapter(DividendDetailActivity mContext, List<String> mDatas) {
    }

    @Override
    public RecylerViewHodle onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_divdent_detail,parent,false);

        RecylerViewHodle hodle = new RecylerViewHodle(view);

        return hodle;
    }

    @Override
    public void onBindViewHolder(RecylerViewHodle holder, int position) {
            holder.tv_time.setText(mDatas.get(position));
          // holder.tv_up_float.setText(mDatas.get(position));
         //  holder.tv_up.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class RecylerViewHodle extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_up_float;
        TextView tv_up;
        public RecylerViewHodle(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
          // tv_up_float = (TextView) itemView.findViewById(R.id.tv_up_float);
          //  tv_up = (TextView) itemView.findViewById(R.id.tv_up);
        }
    }
}