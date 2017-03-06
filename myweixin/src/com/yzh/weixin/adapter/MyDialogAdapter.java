package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yzh.weixin.R;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/4/5.
 */
public class MyDialogAdapter extends BaseAdapter {

    Context context;
    private ArrayList<String> mList = new ArrayList<String>();
    public MyDialogAdapter(Context context , ArrayList<String> mList){
           this.context=context;
           loadData();
    }
    private void loadData() {
        mList.add("移动电话");
        mList.add("电子邮箱");
        mList.add("关于");
        mList.add("帮助");
        mList.add("相册");
        mList.add("新消息提醒");
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
            convertView = View.inflate(context, R.layout.item_user_main, null);
            holder = new ViewHolder();
            holder.tvLeft = (TextView) convertView
                    .findViewById(R.id.tv_left);
            holder.tvRight = (TextView) convertView
                    .findViewById(R.id.tv_right);
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
        public TextView tvRight;
    }
}
