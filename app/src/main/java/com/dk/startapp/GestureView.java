package com.dk.startapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.RelativeLayout;


public class GestureView extends RelativeLayout implements ScaleGestureDetector.OnScaleGestureListener {
    private ScaleGestureDetector scaleGestureDetector;
    public static String TAG="GestureView";

    public GestureView(Context context) {
        super(context);
        scaleGestureDetector=new ScaleGestureDetector(context,this);
    }

    public GestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scaleGestureDetector=new ScaleGestureDetector(context,this);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scaleGestureDetector=new ScaleGestureDetector(context,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        Log.d(TAG,"---onTouchEvent----");
        int actionMasked = event.getActionMasked();
        Log.d(TAG,"---onTouchEvent----actionMasked="+actionMasked);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (actionMasked == 5){
                    Log.d(TAG,"---onTouchEvent---5--Down-");
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (actionMasked == 5){
                    Log.d(TAG,"---onTouchEvent---5--UP-");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"---onTouchEvent---UP-");
                break;
        }
        invalidate();   // 刷新
        return scaleGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.d(TAG,"---onScale--t--");
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.d(TAG,"---onScaleBegin--t--");
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        Log.d(TAG,"---onScaleEnd---t-");
    }
}
