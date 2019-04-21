package com.dk.startapp;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_og);
//        GLSurfaceView demoGlv = (GLSurfaceView) findViewById(R.id.glv_main_demo);
//        demoGlv.setEGLContextClientVersion(2); // 一定要设置
//        demoGlv.setRenderer(new MyRenderer());
//        demoGlv.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
