package com.yzh.weixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yzh.weixin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by luoyongchang on 2016/3/3.
 */
public class CircleAdapter extends BaseAdapter {
    private List<Bean> data = new ArrayList<Bean>();
    private LayoutInflater lif;
    private Context mContext;

    public CircleAdapter(Context context, List<Bean> data) {
        loadData();
        this.lif = LayoutInflater.from(context);
        this.mContext = context;
    }

    private void loadData() {
        for (int i = 0; i < 36; i++) {
            Bean bean = new Bean();
            bean.name = ("效果命名");
            bean.state = ("第" + (i + 1) + "项目");
            data.add(bean);
        }

    }

    class Bean {
        public String name;
        public String state;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Bean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = lif.inflate(R.layout.item_circle_frag, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.items_name);
            holder.tvState = (TextView) convertView.findViewById(R.id.items_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean bean = getItem(position);
        if (position == 0) {
            holder.tvName.setText("跳转测试界面+侧边栏动画");
        } else if (position == 1) {
            holder.tvName.setText("自定义对话框");
        } else if (position == 2) {
            holder.tvName.setText("自定义进度条");
        } else if (position == 3) {
            holder.tvName.setText("自定义吐司");
        }else if (position == 4) {
            holder.tvName.setText("webview");
        }else if (position == 5) {
            holder.tvName.setText("自定义popwindow");
        }else if (position == 6) {
            holder.tvName.setText("流式标签云");
        }else if (position == 7) {
            holder.tvName.setText("轮播条");
        }else if (position == 8) {
            holder.tvName.setText("拍照");
        }else if (position == 9) {
            holder.tvName.setText("拍照+相册选择");
        }else if (position == 10) {
            holder.tvName.setText("瀑布流");
        }else if (position == 11) {
            holder.tvName.setText("科大讯飞语音云");
        }else if (position == 12) {
            holder.tvName.setText("跳转到测试页");
        }else if (position == 13) {
            holder.tvName.setText("分享菜单");
        }else if (position == 14) {
            holder.tvName.setText("小圆点动画");
        }else if (position == 15) {
            holder.tvName.setText("生活圈换乘逻辑布局");
        }else if (position == 16) {
            holder.tvName.setText("动画适配");
        }else if (position == 17) {
            holder.tvName.setText("slidingdrawer");
        }else if (position == 18) {
            holder.tvName.setText("二维码扫描");
        }else if (position == 19) {
            holder.tvName.setText("auto布局适配");
        }else if (position == 20) {
            holder.tvName.setText("布局适配对比");
        }else if (position == 21) {
            holder.tvName.setText("item中的控件点击回调");
        }else if (position == 22) {
            holder.tvName.setText("sortList界面");
        }else if (position == 23) {
            holder.tvName.setText("slidePanel");
        }else if (position == 24) {
            holder.tvName.setText("可扩展gridview加强版");
        }else if (position == 25) {
            holder.tvName.setText("页面");
        }else if (position == 26) {
            holder.tvName.setText("listview条目高度自适应");
        }else if (position == 27) {
            holder.tvName.setText("volley的使用");
        }else if (position == 28) {
            holder.tvName.setText("dialogFragment");
        }else{
            holder.tvName.setText(bean.name);
        }
        holder.tvState.setText(bean.state);
        return convertView;
    }

    class ViewHolder {
        TextView tvName;
        TextView tvState;
    }

}
