package com.yzh.weixin.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.baidu.mapapi.SDKInitializer;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.constant.Constants;
import com.yzh.weixin.R;
import com.yzh.weixin.selectavatar.AvatarImageView;
import com.yzh.weixin.ui.demo.CeshiMap;
import com.yzh.weixin.gaode.GaodeMainAct;
import com.yzh.weixin.utils.bitmap.LocalCacheUtils;
import com.yzh.weixin.utils.bitmap.MyBitmapUtils;


/**
 * Created by cuiqiang on 2016/5/10.
 */
public class FindFrag extends Fragment {

    private LinearLayout btMap;
    private AvatarImageView avatarImageView;
    private MyBitmapUtils myBitmapUtils;
    private LinearLayout btCeshi;
    private ImageView ivCeshi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity());
        View v = inflater.inflate(R.layout.fragment_find, null);
        avatarImageView = (AvatarImageView)v.findViewById(R.id.head_pic);
        btMap = (LinearLayout)v.findViewById(R.id.ll_map);
        btCeshi = (LinearLayout)v.findViewById(R.id.ll_ceshi);
        ivCeshi = (ImageView)v.findViewById(R.id.iv_ceshi);
        btMap.setOnClickListener(turnMap);
        btCeshi.setOnClickListener(GaodeMap);
        initData();
        return v;
    }

    private View.OnClickListener GaodeMap =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            /*GradientDrawable myGrad = (GradientDrawable)ivCeshi.getBackground();
            myGrad.setColor(Color.parseColor("#FFFF00"));*/
            Intent intent = new Intent(getActivity(), GaodeMainAct.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener turnMap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CeshiMap.class);
            startActivity(intent);
        }
    };


    private void initData() {
          myBitmapUtils=new MyBitmapUtils();
         //因为没有服务器，第二次登陆我们以本地缓存为准，
         // 正常情况应该上传服务器，以服务器上下载下来的图片为准
          myBitmapUtils.display(avatarImageView, Constants.HEAD_PIC);
//        avatarImageView.setDialogBackgroundColor("#00AAAA"); //设置对话框的背景色
          avatarImageView.setBtnClickedColor("#FFFF00"); //设置按钮点击后的颜色
          avatarImageView.setAnimResId(R.style.avatar_dialog_animation); //设置dialog显示的动画
//        avatarImageView.setTitleColor("#FFEEAA");  //设置标题的颜色
//        avatarImageView.setBtnBackgroundColor("#FFEEAA"); //设置按钮的背景色
//        avatarImageView.setTitleLineColor("#FFEEAA"); //设置标题下的分割线的颜色
//        avatarImageView.setLineColor("#FFEEAA"); //设置按钮之间的分割线的颜色
//        avatarImageView.setTitlePaddingTopBottom(30); //设置标题的padding
//        avatarImageView.setBtnPaddingTopBottom(30); //设置按钮的padding
//        avatarImageView.setTitleText("testTitle"); //设置标题的文字
//        avatarImageView.setPhotoButtonText("testPhotoText"); //设置拍照按钮的文字
//        avatarImageView.setChoosePicButtonText("testChooseText"); //设置选择照片的文字
//        avatarImageView.setDialogCorner(20); //设置dialog的角度
//        avatarImageView.setBtnTextColor("#FFEEAA"); //设置按钮文本的颜色
//        avatarImageView.setTitleTextSize(30); //设置标题的文字大小
//        avatarImageView.setBtnTextSize(30); //设置按钮的文字大小
          avatarImageView.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                Toast.makeText(getActivity(),"设置新的头像成功",Toast.LENGTH_SHORT).show();
            }
          });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //在拍照、选取照片、裁剪Activity结束后，调用的方法

    }

    public void resultAgent(int requestCode, int resultCode, Intent data){
        if(avatarImageView != null){
            Log.d("mmet","FindFrag.onActivityResult");
            avatarImageView.onActivityResult(requestCode,resultCode,data);
            Bitmap bitmap = ((BitmapDrawable)avatarImageView.getDrawable()).getBitmap();
            if(bitmap!=null){
                LocalCacheUtils.setCache(bitmap,Constants.HEAD_PIC);
            }
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("FindFrag"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("FindFrag");
    }
}
