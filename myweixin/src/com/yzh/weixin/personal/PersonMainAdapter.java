package com.yzh.weixin.personal;

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
public class PersonMainAdapter extends BaseAdapter {
    Context context;
    private String email;
    private String phone;
    private ArrayList<String> mList = new ArrayList<String>();

    public PersonMainAdapter(Context context){
           this.context=context;
           loadData();
    }
    private void loadData() {

        mList.add("移动电话");
        mList.add("电子邮箱");
        mList.add("关于");
        mList.add("帮助");
        mList.add("检查更新");
        mList.add("修改密码");
        mList.add("退出登录");
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
            convertView = View.inflate(context, R.layout.item_personal_main, null);
            holder = new ViewHolder();
            holder.ivRight = (ImageView) convertView
                    .findViewById(R.id.iv_right);
            holder.tvLeft = (TextView) convertView
                    .findViewById(R.id.tv_left);
            holder.tvRight = (TextView) convertView
                    .findViewById(R.id.tv_right);
            holder.tvCenter = (TextView) convertView
                    .findViewById(R.id.tv_center);
            if (position==0){
                holder.tvRight.setVisibility(View.VISIBLE);
                holder.ivRight.setVisibility(View.GONE);
                holder.tvRight.setText(phone);
            }else if (position==1){
                holder.tvRight.setVisibility(View.VISIBLE);
                holder.ivRight.setVisibility(View.GONE);
                holder.tvRight.setText(email);
            }else if(position==6){
                holder.tvRight.setVisibility(View.GONE);
                holder.ivRight.setVisibility(View.GONE);
                holder.tvLeft.setVisibility(View.GONE);
                holder.tvCenter.setVisibility(View.VISIBLE);
            }
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
