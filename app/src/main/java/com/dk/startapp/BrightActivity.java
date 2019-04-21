package com.dk.startapp;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class BrightActivity extends AppCompatActivity {

    private float startY = 0;//手指按下时的Y坐标
    private float startX = 0;//手指按下时的Y坐标

    private TextView brightnessTextView;
    private View mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bright);

        mVideoView = LayoutInflater.from(this).inflate(R.layout.activity_bright, null);
        setContentView(mVideoView);
        brightnessTextView = (TextView) mVideoView.findViewById(R.id.text);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }



    /*
     * 设置屏幕亮度
     * 0 最暗
     * 1 最亮
     */
    public void setBrightness(float brightness) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = lp.screenBrightness + brightness / 255.0f;
        if (lp.screenBrightness > 1) {
            lp.screenBrightness = 1;
        } else if (lp.screenBrightness < 0.1) {
            lp.screenBrightness = (float) 0.1;
        }
        getWindow().setAttributes(lp);

        float sb = lp.screenBrightness;
        brightnessTextView.setText((int) Math.ceil(sb * 100) + "%");
    }

    private float dY = 0;
    //点击横坐标
    //抬起纵坐标
    private float uY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int screenWidth = mVideoView.getWidth();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float endY = event.getY();
                float distanceY = startY - endY;
                uY = event.getY();
                if (startX > screenWidth / 2) {
                    //右边
                    //在这里处理音量
                    if (Math.abs(uY - dY) > 25)
                        setVolume(uY - dY);

                } else {
                    //屏幕左半部分上滑，亮度变大，下滑，亮度变小

                    final double FLING_MIN_DISTANCE = 0.5;
                    final double FLING_MIN_VELOCITY = 0.5;
                    if (distanceY > FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY) {
                        setBrightness(10);
                    }
                    if (distanceY < FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY) {
                        setBrightness(-10);
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    //媒体音量管理
    private AudioManager audioManager;
    //手势调节音量
    private void setVolume(float vol) {
        if (vol < 0) {//增大音量
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,
                    AudioManager.FX_FOCUS_NAVIGATION_UP);
        } else if (vol > 0) {//降低音量
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,
                    AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
    }

}
