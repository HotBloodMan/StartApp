package com.dk.startapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TabClickActivity extends AppCompatActivity {
    View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_click);
        LinearLayout llroot=findViewById(R.id.ll_control);
         decorView = getWindow().getDecorView();
        Button btn=findViewById(R.id.btn_tab);
        llroot.setOnTouchListener(new MyClickListener(new MyClickListener.MyClickCallBack() {
            @Override
            public void oneClick() {
                Toast.makeText(TabClickActivity.this, "单击",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doubleClick() {
                Toast.makeText(TabClickActivity.this, "双击",
                        Toast.LENGTH_SHORT).show();
            }
        }));
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TabClickActivity","-------->>>onClick");
//                fullScreen(decorView);
//            }
//        });


    }

    boolean fullScreen=true;
    public void fullScreen(View v) {
//        Toast.makeText(this, "change", Toast.LENGTH_SHORT).show();
        View decorView = getWindow().getDecorView();
        int uiFlags = 0;
        if (fullScreen) {
            uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY //for touch screen without navigation
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav fullScreen
                    | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status fullScreen

        }
        decorView.setSystemUiVisibility(uiFlags);
        fullScreen = !fullScreen;
    }
}
