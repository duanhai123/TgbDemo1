package com.geebit.app1.activity;
/* data: 2017-01-12
 * author: 段海鹏
 * ui: 1型的详情页面
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geebit.app1.R;
import com.geebit.app1.bean.HoldingDetail1Bean;
import com.geebit.app1.utils.ConstantsRedBaby;
import com.geebit.app1.utils.CrmApiUtil;
import com.geebit.app1.view.MyApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class HoldingDetail1Activity extends BaseActivity {

    private View view;
    private RecyclerView recyleviewHd;
    private CommonAdapter adapter;
    private String uid;
    private int asset_type;
    private String deal_date;
    private double deal_amount;
    private int trans_type;
    private List<HoldingDetail1Bean.DataBean> data;

    @Override
    protected void initoView() {
        recyleviewHd = (RecyclerView) view.findViewById(R.id.rv_hold_detail);


    }

    @Override
    protected void initData() {
        new Thread(){
            @Override
            public void run() {
                postJson();
            }
        }.start();

        adapter = new CommonAdapter(this,R.layout.item_holding_detail1,data) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                holder.setText(R.id.tv_time,"11111");
            }

        };
        LinearLayoutManager manager = new LinearLayoutManager(HoldingDetail1Activity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyleviewHd.setLayoutManager(manager);
        recyleviewHd.setAdapter(adapter);


    }

    private void postJson() {
        HashMap map = new HashMap();
        String accountType = ConstantsRedBaby.ACCOUNT_TYPE;
        String urlServer =" http://192.168.1.102:8080/api/orders/incomeStatement";
        uid = MyApp.SP.getString("uid", "");
           if (uid!=null){
               map.put("user_id",1);
               map.put("account_type",accountType);
               JSONObject j = new JSONObject(map);
               String json = j.toString();
               String onlyJson = CrmApiUtil.postOnlyJson(
                      urlServer, json);
               Gson gson = new Gson();
               Type type = new TypeToken<HoldingDetail1Bean>(){}.getType();
               HoldingDetail1Bean hdBean = gson.fromJson(onlyJson, type);
               data = hdBean.getData();
               //资产类型
               asset_type = hdBean.getData().get(0).getAsset_type();

               //创建时间
               deal_date = hdBean.getData().get(0).getDeal_date();
               //成交金额
               deal_amount = hdBean.getData().get(0).getDeal_amount();
               //事务类型
               trans_type = hdBean.getData().get(0).getTrans_type();

           }

    }

    @Override
    public View initView() {
        view = View.inflate(this, R.layout.activity_holding_detail1,null);
        return view;
    }
}
