package com.yzh.weixin.common.view;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.agent.PopwindowAgent;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class TestPopwindow extends PopupWindow implements OnItemClickListener{
    // 根视图
    private View mRootView;
    private LayoutInflater mInflater;

    // ArrayList数组，listview原始数据
    private ArrayList<PopwindowAgent> mArrayList;

    // 数据接口
    OnGetData mOnGetData;

    // listview适配器
    SelfTypeAdapt mSelfTypeAdapt;

    private int mnSeclectItem = 0;

    public TestPopwindow(Activity context) {
        super(context);
        InitData(context);
        InitUI();
    }

    // 数据接口设置,数据源接口传入
    public void setOnData(OnGetData sd) {
        mOnGetData = sd;
        mArrayList = new ArrayList<PopwindowAgent>();
        if (mOnGetData != null) {
            mArrayList = mOnGetData.onArrayList();
            mnSeclectItem = mOnGetData.onSeclectItem();
        }
    }

    // 数据接口抽象方法
    public interface OnGetData {
        abstract ArrayList<PopwindowAgent> onArrayList();

        abstract int onSeclectItem();

        abstract void onDataCallBack(int nSectlect,
                                     ArrayList<PopwindowAgent> mArrayList);
    }

    private void InitData(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = mInflater.inflate(R.layout.my_popwindow, null);
        setContentView(mRootView);

        this.setWidth(LayoutParams.FILL_PARENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);

        // 设置PopUpWindow弹出的相关属性
        setTouchable(true);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        update();

        getContentView().setFocusableInTouchMode(true);
        getContentView().setFocusable(true);
        setAnimationStyle(R.style.AppBaseTheme);
    }

    private void InitUI() {
        ListView listView = (ListView) mRootView
                .findViewById(R.id.listView1);
        mSelfTypeAdapt = new SelfTypeAdapt(mRootView.getContext());
        listView.setAdapter(mSelfTypeAdapt);
        listView.setOnItemClickListener(this);
    }

    public class SelfTypeAdapt extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mLayoutInflater;

        public SelfTypeAdapt(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mArrayList == null ? 0 : mArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = (LinearLayout) mLayoutInflater.inflate(
                        R.layout.item_popwindow, null);
                viewHolder.textView = (TextView) convertView
                        .findViewById(R.id.textViewTest);
                viewHolder.imageView = (ImageView) convertView
                        .findViewById(R.id.imageView1);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            PopwindowAgent itemTest = new PopwindowAgent();
            itemTest = mArrayList.get(position);

            viewHolder.textView.setText(itemTest.getImageViewID());
            viewHolder.imageView.setImageResource(itemTest.getImageViewID());
            return convertView;
        }
    }

    // 内部类实现，提升listview效率
    class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        mOnGetData.onDataCallBack(position, mArrayList);
        dismiss();

    }
}
