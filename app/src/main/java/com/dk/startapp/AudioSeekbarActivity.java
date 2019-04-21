package com.dk.startapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class AudioSeekbarActivity extends AppCompatActivity {

    private ContentObserver mVoiceObserver;
    LinearLayout lRoot;
    private SeekBar seekbar_video;
    int  default_unit=5;
    private int maxAmount=1500;//最大值
    private int quota;//当前滑动到的值
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
        setContentView(R.layout.activity_audio_seekbar);

//        if (Build.VERSION.SDK_INT >= 23) {
//            int REQUEST_CODE_CONTACT = 101;
//            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            //验证是否许可权限
//            for (String str : permissions) {
//                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
//                    //申请权限
//                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
//                }
//            }
//        }
        lRoot=findViewById(R.id.ringtone_parent);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);//获取媒体系统服务
        seekbar_video= (SeekBar) findViewById(R.id.seek_bar_video123);//注册ID
        seekbar_video.setMax(maxAmount);//设置最大音量
//        addDefaultScreenArea(seekbar_video,200,400,0,0);
        addDefaultScreenArea(seekbar_video,0,0,100,100);
        setListener();

//        seekbar_video.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));// 当前的媒体音量
//        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        Log.d("AudioSeekbarActivity","-------currVolume="+currVolume);
//        seekbar_video.setProgress(currVolume*100/15) ;

//        myRegisterReceiver();//注册同步更新的广播
//
//        registerVolumeChangeReceiver();
//
//
//        addDefaultScreenArea(seekbar_video,50,50,350,350);
//        addTouchZone(seekbar_video);
        seekbar_video.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {
//                Log.v("AudioSeekbarActivity2", "mVoiceSeekBar onStopTrackingTouch = ");
            }

            public void onStartTrackingTouch(SeekBar arg0) {
//                Log.v("AudioSeekbarActivity2", "mVoiceSeekBar onStartTrackingTouch = ");
            }
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
//                Log.v("AudioSeekbarActivity2", "mVoiceSeekBar onProgressChanged = ");
//                Log.v("AudioSeekbarActivity2", "mVoiceSeekBar onProgressChanged = arg2="+arg2);
                Log.v("AudioSeekbarActivity2", "mVoiceSeekBar onProgressChanged = arg1="+arg1+" arg2="+arg2);
//                if(arg2){
//                    AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                    Log.v("AudioSeekbarActivity2", "mVoiceSeekBar max progress = "+arg1);
//                    //系统音量和媒体音量同时更新
//                    audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, arg1, 0);
//                    audioManager.setStreamVolume(3, arg1, 0);//  3 代表  AudioManager.STREAM_MUSIC
//                }
//                refreshView(arg1);
            }
        });
        mVoiceObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.d("AudioSeekbarActivity2","----222------>>>onChange=");
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) ;// 当前的媒体音量
                Log.d("AudioSeekbarActivity2","----222------>>>currVolume="+currVolume);
                seekbar_video.setProgress(currVolume*100/15) ;
                //或者你也可以用媒体音量来监听改变，效果都是一样的。
                //mVoiceSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            }
        };
    }

    private void refreshView(int quota) {
        Log.d("AudioSeekbarActivity2","------refreshView=");
        if (quota % default_unit > 0 && quota != maxAmount ) {
            int remainder = quota % default_unit;
            Log.d("AudioSeekbarActivity2","------remainder="+remainder);
            if (remainder > default_unit / 2 && default_unit * (((int) (quota / default_unit)) + 1) <= maxAmount) {
                quota = default_unit * (((int) (quota / default_unit)) + 1);
            } else {
                quota = default_unit * (int) (quota / default_unit);
            }
            Log.d("AudioSeekbarActivity2","------quota="+quota);
            seekbar_video.setProgress(quota);
            return;
        }
    }


    private void setListener() {
        lRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        seekbar_video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
    }

    public void addTouchZone(View vx){
        vx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Rect rect = new Rect();
                v.getHitRect(rect);
                if((event.getY()>=(rect.top-50)) &&
                        (event.getY()<=(rect.bottom+50))){
                    float y = rect.top + rect.height()/2+50;
                    Log.d("AudioSeekbarActivity3","---------->>>y="+y);
//                    float x = event.getX()-rect.left;
                    float x = event.getX();
                    Log.d("AudioSeekbarActivity3","---------->>>x="+x);
                    if(x <0) {
                        Log.d("AudioSeekbarActivity3","---------->>>3333");
                        x=0;
                    } else if(x > rect.width()) {
                        Log.d("AudioSeekbarActivity3","---------->>>444");
                        x= rect.width();
                        Log.d("AudioSeekbarActivity3","---------->>>444--x="+x);
                    }
                    MotionEvent me = MotionEvent.obtain(event.getDownTime(), event.getEventTime(),
                            event.getAction(), x, y, event.getMetaState());

                    Log.d("AudioSeekbarActivity3","---------->>555--x="+v.onTouchEvent(me));
                    return v.onTouchEvent(me);
                }
                return false;
            }
        });
    }

    private float getXPosition(SeekBar seekBar){
        float val = (((float)seekBar.getProgress() * (float)(seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax());
        float offset = seekBar.getThumbOffset()*2;

        int textWidth = seekBar.getWidth();
        float textCenter = (textWidth/2.0f);

        float newX = val+offset - textCenter;

        return newX;
    }


    /**
     * 增加控件的可点击范围，最大范围只能是父布局所包含的的区域
     */
    public static void addDefaultScreenArea(final View view, final int top,final int bottom,final int left,final int right) { // 增大checkBox的可点击范围
        Log.v("AudioSeekbarActivity2", "----->>>addDefaultScreenArea = ");
        final View parent = (View) view.getParent();
        parent.post(new Runnable() {
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);
                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });

    }




    MyVolumeReceiver  mVolumeReceiver;
    private void myRegisterReceiver(){
        Log.d("AudioSeekbarActivity2","----222------>>>myRegisterReceiver=");
          mVolumeReceiver = new MyVolumeReceiver() ;
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
        registerReceiver(mVolumeReceiver, filter) ;
    }
    /**
     * 处理音量变化时的界面显示
     * @author long
     */
    private class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果音量发生变化则更改seekbar的位置
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
                Log.d("AudioSeekbarActivity2","---------->>>currVolume=");
                AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                int currVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) ;// 当前的媒体音量
                Log.d("AudioSeekbarActivity2","---------->>>currVolume="+currVolume);
                seekbar_video.setProgress(currVolume*100/15) ;
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        myRegisterReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(null!=mVolumeReceiver){
            unregisterReceiver(mVolumeReceiver);
        }
    }

    SettingsContentObserver mSettingsContentObserver;
    private void registerVolumeChangeReceiver() {
        mSettingsContentObserver = new SettingsContentObserver(AudioSeekbarActivity.this, new Handler());
        getContentResolver()
                .registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mSettingsContentObserver);
    }

    public class SettingsContentObserver extends ContentObserver {
Context context;
 public SettingsContentObserver(Context c, Handler handler) {
            super(handler);
            context = c;
 }
@Override
public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
 }
@Override
public void onChange(boolean selfChange) {
super.onChange(selfChange);
Log.d("AudioSeekbarActivity2","-----onChange------");
AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
Log.d("AudioSeekbarActivity2", "音量：" + currentVolume);
//audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND);
 }
    }
}