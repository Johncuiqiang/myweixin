package com.yzh.weixin.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.yzh.weixin.R;
import com.yzh.weixin.photo.utils.PhotoUtils;


import java.io.File;

/**
 * Created by diy on 2016/5/17
 */
public class FragB extends Fragment {

    private PhotoActivity activity;
    private Button takePhoto;
    private ImageView tmpPhoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (PhotoActivity) getActivity();
        View view = inflater.inflate(R.layout.frag_b, null);
        tmpPhoto = (ImageView) view.findViewById(R.id.tmpPhoto);
        takePhoto = (Button) view.findViewById(R.id.takephoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        initData();
        return view;
    }

    private void initData() {
        activity.setOnChooseCompleteListener(new PhotoActivity.OnChooseCompleteListener() {
            @Override
            public void onComplete(String... path) {
                if (path != null) {
                    String headPicPath = PhotoUtils.getSmallBitmapAndSave(path[0]);
                    Bitmap bitmap = PhotoUtils.getBitmapFromFile(headPicPath);
                    tmpPhoto.setImageBitmap(bitmap);
                }
            }
        });
    }

    /**
     * 获取手机拍照的图片
     *
     * @author: WangXF
     * @date:2014-8-4
     */
    public void takePhoto() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File dir = new File(activity.getCameraDir());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(activity.getFilePath());
                Uri uri = Uri.fromFile(file);
                // 准备intent，并 指定 新 照片 的文件名（photoUri）
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                // 启动拍照的窗体。并注册 回调处理。
                activity.startActivityForResult(intent, PhotoActivity.REQUEST_CODE_TAKE_PICTURE);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(activity, "执行失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "存储卡不可用", Toast.LENGTH_SHORT).show();
        }
    }


}
