package com.yzh.weixin.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;


public class PhotoActivity extends FragmentActivity {

    private FrameLayout flContent;
    private FragB mFragB;
    private FragmentManager fragmentMgr;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;
    /**
     * 用户选择完成之后的回调接口
     */
    private OnChooseCompleteListener onChooseCompleteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_photo);

        flContent = (FrameLayout) findViewById(R.id.fl);

        initFragment();

    }


    private void initFragment() {
        fragmentMgr = getSupportFragmentManager();
        mFragB = new FragB();
        switchContent(mFragB);
    }

    private Fragment current;                               // 当前的fragment
    private boolean isFirstShowHeartFragment = true;        //是否是第一次切换到心动fragment初始化数据使用

    /**
     * 切换当前显示的fragment
     */
    public void switchContent(Fragment fragment) {
        if (current != fragment) {
            FragmentTransaction transaction = fragmentMgr.beginTransaction();
            if (current != null) {
                transaction.hide(current);
            }
            if (!fragment.isAdded()) { // 先判断是否被add过
                transaction.add(R.id.fl, fragment).commitAllowingStateLoss();
            } else {
                transaction.show(fragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
            current = fragment;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PICTURE:
                handleTakePhotoResult(data, true);
                break;
            case REQUEST_CODE_CROP_IMAGE:
                if (data != null) {
                    handleCropPhotoResult(data);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 处理相机拍照获取的图片
     */
    private void handleTakePhotoResult(Intent data, boolean isCrop) {
        try {
            String path = getFilePath();
            Log.d("path","  "+path);
            if (isCrop) {
                startCropImage(path);
            } else {
                if (onChooseCompleteListener != null) {
                    onChooseCompleteListener.onComplete(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 处理剪裁图片后的结果
     */
    private void handleCropPhotoResult(Intent data) {
        String path = data.getStringExtra(ClipActivity.IMAGE_PATH);
        if (path == null) {
            return;
        }
        if (onChooseCompleteListener != null) {
            onChooseCompleteListener.onComplete(path);
        }
    }

    //    public Uri getPhotoUri() {
    //        ContentValues values = new ContentValues();
    //        return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    //    }

    public String getFilePath() {
        return getCameraDir() + "/a";
    }

    public String getCameraDir(){
        return Environment.getExternalStorageDirectory().getPath() + "/photo";
    }

    /**
     * 开始裁切选中的图片
     *
     * @author: WangXF
     * @date:2014-8-4
     */
    private void startCropImage(String photoPath) {
        Intent intent = new Intent(this, ClipActivity.class);
        intent.putExtra(ClipActivity.IMAGE_PATH, photoPath);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    public interface OnChooseCompleteListener {
        void onComplete(String... path);
    }

    public void setOnChooseCompleteListener(OnChooseCompleteListener onChooseCompleteListener) {
        this.onChooseCompleteListener = onChooseCompleteListener;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("method","onStart");
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("method", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("method", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("method", "onDestroy");
    }
}
