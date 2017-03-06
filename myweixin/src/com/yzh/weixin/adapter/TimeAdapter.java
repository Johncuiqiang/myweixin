package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.bean.Time;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/4/5.
 */
public class TimeAdapter extends BaseAdapter {
    Context context;
    private ArrayList<Time> mList;
    public TimeAdapter(Context context , ArrayList<Time> mList){
           this.context=context;
           this.mList=mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Time getItem(int position) {
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
            convertView = View.inflate(context, R.layout.item_time, null);
            holder = new ViewHolder();
            holder.tvContent = (TextView) convertView
                    .findViewById(R.id.tv_content);
            holder.tvTime = (TextView) convertView
                    .findViewById(R.id.tv_time);
            holder.ivPic= (ImageView) convertView
                    .findViewById(R.id.iv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String text = getItem(position).getText();
        String time = getItem(position).getTime();
        Integer img = getItem(position).getImg();
        holder.tvContent.setText(text);
        holder.tvTime.setText(time);
        holder.ivPic.setImageResource(img);
        return convertView;
    }
    class ViewHolder {
        public TextView tvContent;
        public TextView tvTime;
        public ImageView ivPic;
    }
}
