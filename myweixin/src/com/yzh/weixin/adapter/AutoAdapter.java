package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.utils.DisplayUtil;
import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/4/5.
 */
public class AutoAdapter extends BaseAdapter {
    Context context;
    private ArrayList<String> mList = new ArrayList<String>();
    private int sheight;
    public AutoAdapter(Context context,int height){
           this.context=context;
           this.sheight=height;
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
            convertView = View.inflate(context, R.layout.item_auto_lv, null);
            holder = new ViewHolder();
            holder.tvLeft = (TextView) convertView
                    .findViewById(R.id.tv_left);
            holder.ll = convertView
                    .findViewById(R.id.ll);
            holder.tvHeight = (ImageView) convertView
                    .findViewById(R.id.tv_height);
            ViewGroup.LayoutParams params =  holder.tvHeight.getLayoutParams();
            params.height =sheight/mList.size();
            holder.tvHeight.setLayoutParams(params);
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
        public ImageView tvHeight;
        public View ll;
    }
}
