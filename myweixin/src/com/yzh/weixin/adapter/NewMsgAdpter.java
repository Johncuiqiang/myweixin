package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yzh.weixin.R;


public class NewMsgAdpter extends BaseAdapter {
	protected Context context;
	LayoutInflater mInflater;

	public NewMsgAdpter(Context ctx) {
		context = ctx;
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_new_msg, parent, false);
			TextView txtnum = (TextView) convertView
					.findViewById(R.id.unread_msg_number);
			if (position == 0) {
				txtnum.setVisibility(View.VISIBLE);
			} else {
				txtnum.setVisibility(View.GONE);
			}
		}
		return convertView;
	}
}
