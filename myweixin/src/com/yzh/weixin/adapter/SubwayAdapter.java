package com.yzh.weixin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.bean.Type;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/4/5.
 */
public class SubwayAdapter extends BaseAdapter {
    Context context;
    private ArrayList<Type> mList = new ArrayList<Type>();
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;

    public SubwayAdapter(Context context, ArrayList<Type> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Type getItem(int position) {
        return mList.get(position);
    }

    //每个convert view都会调用此方法，获得当前所需要的view样式
    public int getItemViewType(int position) {
        int p=mList.get(position).type;
           if (p == 0) {
               return TYPE_1;
           } else {
               return TYPE_2;
           }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder1 holder1 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_1:
                    convertView = View.inflate(context, R.layout.item_subway_mid, null);
                    holder1 = new ViewHolder1();
                    holder1.ivFirstm = (ImageView) convertView
                            .findViewById(R.id.iv_first);
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = View.inflate(context, R.layout.item_subway_start, null);
                    holder = new ViewHolder();
                    holder.ivFirst = (ImageView) convertView
                            .findViewById(R.id.iv_first);
                    holder.ivEnd = (ImageView) convertView
                            .findViewById(R.id.iv_end);
                    convertView.setTag(holder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_1:
                    holder1 = (ViewHolder1)convertView.getTag();
                    break;
                case TYPE_2:
                    holder  = (ViewHolder)convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TYPE_1:
                int test1 = getItem(position).mList;
                holder1.ivFirstm.setImageResource(test1);
                break;
            case TYPE_2:
                int test = getItem(position).mList;
                holder.ivFirst.setImageResource(test);
                holder.ivEnd.setImageResource(test);
                break;
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView ivFirst;
        public ImageView ivEnd;
    }

    class ViewHolder1 {
        public ImageView ivFirstm;
    }
}
