package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.bean.Share;


import java.util.ArrayList;


/**
 * Created by zhangyipeng on 16/1/16.
 */
public class ShareMenuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Share> list;

    public ShareMenuAdapter(Context context, ArrayList<Share> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gv_share, null);
            holder = new ViewHolder();
            holder.tvDesc = (TextView) convertView.findViewById(R.id.share_desc);
            holder.tvIcon = (ImageView) convertView.findViewById(R.id.share_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDesc.setText(list.get(position).getName());
        Integer icon = list.get(position).getIcon();
        if (icon != null) {
            holder.tvIcon.setImageResource(icon);
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvDesc;
        ImageView tvIcon;
    }
}
