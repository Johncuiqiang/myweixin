package com.yzh.weixin.flowcloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import com.yzh.weixin.R;
import com.yzh.weixin.ui.demo.FlowCloudActivity;

import java.util.List;

/**
 * @author fyales
 * @since 8/26/15.
 */
public class TagBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<FlowCloudActivity.Beanimg> mList;

    public TagBaseAdapter(Context context, List<FlowCloudActivity.Beanimg> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public FlowCloudActivity.Beanimg getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tag_view, null);
            holder = new ViewHolder();
            holder.tagBtn = (Button) convertView.findViewById(R.id.tag_btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        final FlowCloudActivity.Beanimg beanimg = getItem(position);
        holder.tagBtn.setText(beanimg.texts);
        holder.tagBtn.setBackgroundResource(beanimg.img);
        return convertView;
    }

    static class ViewHolder {
        Button tagBtn;
    }
}
