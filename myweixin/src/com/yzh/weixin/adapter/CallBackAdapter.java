package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.yzh.weixin.R;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/4/5.
 */
public class CallBackAdapter extends BaseAdapter {
    private ArrayList<String> mList = new ArrayList<String>();
    private LayoutInflater mInflater;
    private MyClickListener mListener;
    private YmClickListener ylistener;
    public CallBackAdapter(Context context, ArrayList<String> mList,MyClickListener listener,YmClickListener ylistener) {
        mInflater = LayoutInflater.from(context);
        this.mList = mList;
        mListener = listener;
        this.ylistener=ylistener;
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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_call_back, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textView1);
            holder.button = (Button) convertView.findViewById(R.id.button1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mList.get(position));
        holder.button.setOnClickListener(mListener);
        holder.textView.setOnClickListener(ylistener);
        holder.button.setTag(position);
        return convertView;
    }

    class ViewHolder {
        public TextView textView;
        public Button button;
    }
    /**
      * 用于回调的抽象类
      * @author Ivan Xu
      * 2014-11-26
      */
    public static abstract class MyClickListener implements View.OnClickListener {
    /**
     * 基类的onClick方法
     */
    @Override
    public void onClick(View v) {
           myOnClick((Integer) v.getTag(), v);
    }
    public abstract void myOnClick(int position, View v);

    }
    /**
     * 用于回调的抽象类
     * @author Ivan Xu
     * 2014-11-26
     */
    public static abstract class YmClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick(v);
        }
        public abstract void myOnClick( View v);

    }
}
