package com.dk.startapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;

public class GestureActivity extends AppCompatActivity implements ScaleGestureDetector.OnScaleGestureListener {
    public static String TAG="GestureView";
    private ScaleGestureDetector scaleGestureDetector;
    Button btnGes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        btnGes=  findViewById(R.id.btn_gesture01);
        scaleGestureDetector=new ScaleGestureDetector(this,this);
        btnGes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return scaleGestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scaleGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.d(TAG,"---onScale----");
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.d(TAG,"---onScaleBegin----");
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        Log.d(TAG,"---onScaleEnd----");
    }
}
