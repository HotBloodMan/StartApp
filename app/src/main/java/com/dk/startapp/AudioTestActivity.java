package com.dk.startapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AudioTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_test);
        myRegisterReceiver();
    }


   MyVolumeReceiver mVolumeReceiver;
    private void myRegisterReceiver(){
        Log.d("AudioTestActivity","----222------>>>myRegisterReceiver=");
        mVolumeReceiver = new MyVolumeReceiver() ;
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
        registerReceiver(mVolumeReceiver, filter) ;
    }


    private class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果音量发生变化则更改seekbar的位置
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
                Log.d("AudioTestActivity","------VOLUME_CHANGED_ACTION---->>>currVolume=");
                AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                int currVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) ;// 当前的媒体音量
                Log.d("AudioTestActivity","---------->>>currVolume="+currVolume);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ;if(mVolumeReceiver!=null){
            unregisterReceiver(mVolumeReceiver);
        }
    }
}
