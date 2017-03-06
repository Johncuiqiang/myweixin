package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.iflytek.cloud.*;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.common.MyToast;

/**
 * Created by cuiqiang on 2016/5/23.
 */
public class VoiceAct extends Activity {
    private VoiceAct mactivity;
    private Context context = this;
    private Button b1;
    private Button b2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_voice);
        // 将“12345678”替换成您申请的 APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与 appid 之间添加任务空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=574269ae");
        initView();
    }
    public void  initView(){
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(listen);
        b2.setOnClickListener(speak);
    }
    private View.OnClickListener listen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startListen();
        }
    };
    private View.OnClickListener speak = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startSpeak();
        }
    };
    /**
     * 本地听写
     *
     *
     */
   /* public void startWrite() {
        //1.创建RecognizerDialog对象
        SpeechRecognizer mIat = new SpeechRecognizer.createRecognizer(context,null);
        //2.设置accent、language等参数
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解//结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mIat.startListening(mRecoListener);
    }
        private RecognizerListener mRecoListener = new RecognizerListener(){
            //听写结果回调接口(返回Json格式结果，用户可参见 附录13.1)；
            //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
            //关于解析Json的代码可参见Demo中JsonParser类；
            //isLast等于true时会话结束。
            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                String result = results.getResultString();
                System.out.println("识别结果:" + result);
                System.out.println("识别结果isLast:" + isLast);
                MyToast.show(result,R.drawable.weather_bigsonw,mactivity);
            }
            //会话发生错误回调接口
            @Override
            public void onError(SpeechError arg0) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }

            @Override
            public void onVolumeChanged(int i, byte[] bytes) {

            }

            @Override
            public void onBeginOfSpeech() {

            }

            @Override
            public void onEndOfSpeech() {

            }
        };*/
    /**
     * 语音ui输入
     *
     *
     */
    public void startListen() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解//结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {

            //听写结果回调接口(返回Json格式结果，用户可参见 附录13.1)；
            //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
            //关于解析Json的代码可参见Demo中JsonParser类；
            //isLast等于true时会话结束。
            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                String result = results.getResultString();
                System.out.println("识别结果:" + result);
                System.out.println("识别结果isLast:" + isLast);
                MyToast.show(result,R.drawable.weather_bigsonw,mactivity);
            }

            @Override
            public void onError(SpeechError arg0) {

            }

        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }


    /**
     * 语音合成
     *
     *
     */
    public void startSpeak() {
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer
                .createSynthesizer(context, null);
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyun"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./iflytek.pcm");
        //3.开始合成
        mTts.startSpeaking("科大讯飞,让世界聆听我们的声音", mSynListener);
    }
    private SynthesizerListener mSynListener= new SynthesizerListener(){
        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {
            MyToast.show("说完了",R.drawable.weather_bigsonw,mactivity);
        }

        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    };
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("VoiceAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("VoiceAct");
        MobclickAgent.onPause(this);
    }
}
