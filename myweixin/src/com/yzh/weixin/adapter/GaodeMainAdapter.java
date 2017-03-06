package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;


import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/4/5.
 */
public class GaodeMainAdapter extends BaseAdapter {
    Context context;
    private String email;
    private String phone;
    private ArrayList<String> mList = new ArrayList<String>();

    public GaodeMainAdapter(Context context){
           this.context=context;
           loadData();
    }
    private void loadData() {
        mList.add("定位");
        mList.add("精准定位");
        mList.add("路径规划");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_gaode_main, null);
            holder = new ViewHolder();
            holder.ivRight = (ImageView) convertView
                    .findViewById(R.id.iv_right);
            holder.tvLeft = (TextView) convertView
                    .findViewById(R.id.tv_left);
            holder.tvRight = (TextView) convertView
                    .findViewById(R.id.tv_right);
            holder.tvCenter = (TextView) convertView
                    .findViewById(R.id.tv_center);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String test = getItem(position);
        holder.tvLeft.setText(test);
        return convertView;
    }
    class ViewHolder {
        public TextView tvLeft;
        public ImageView ivRight;
        public TextView tvRight;
        public TextView tvCenter;
    }
}
