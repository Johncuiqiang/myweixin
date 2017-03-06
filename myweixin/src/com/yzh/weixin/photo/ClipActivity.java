package com.yzh.weixin.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.photo.utils.PhotoUtils;


public class ClipActivity extends BaseExActivity implements OnClickListener {
	public static final String IMAGE_PATH = "image_path";
	public static final String ORIGINAL_IMAGE_PATH = "original_image_path";

	private String image_path;
	private String original_image_path;

	private TextView btn_clip_cancel;
	private TextView btn_clip_submit;
	private ClipImageView src_pic;
	private Bitmap newbitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_photo_clip);
		initView();
		initEvent();
		fillData();
	}

	private void initView() {
		btn_clip_cancel = (TextView) findViewById(R.id.btn_clip_cancel);
		btn_clip_submit = (TextView) findViewById(R.id.btn_clip_submit);
		src_pic = (ClipImageView) findViewById(R.id.src_pic);
	}

	private void initEvent() {
		btn_clip_cancel.setOnClickListener(this);
		btn_clip_submit.setOnClickListener(this);
	}

	private void fillData() {
		image_path = getIntent().getStringExtra(IMAGE_PATH);
		original_image_path = image_path;
		// 把图片旋转为正的方向
		newbitmap = PhotoUtils.getSmallBitmap(image_path);
		src_pic.setImageBitmap(newbitmap);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clip_cancel:
			image_path = null;
			break;
		case R.id.btn_clip_submit:
			// 获取裁切的图片
			Bitmap bitmap = src_pic.clip();
			image_path = PhotoUtils.savePhotoToSDCard(bitmap);
			break;
		}
		Bundle extras = new Bundle();
		Intent intent = new Intent();
		intent.putExtras(extras);
		intent.putExtra(IMAGE_PATH, image_path);
		intent.putExtra(ORIGINAL_IMAGE_PATH, original_image_path);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (newbitmap != null && !newbitmap.isRecycled()) {
			newbitmap.recycle();
			newbitmap = null;
		}
	}
}
