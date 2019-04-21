package com.dk.startapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideActionBar();

        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(this);
    }


    String baseUrlPath="/storage/ext_sd/videotest/";
    String[] urlVideoArrays={baseUrlPath+"tt.mp4",baseUrlPath+"wobushiyaoshen.mp4"
            ,baseUrlPath+"xiaoyintao.mp4",baseUrlPath+"zipai.mp4"};
    @Override
    public void onClick(View view) {
        Log.e("TAG", "----------->>>onClick: ");
//        Intent intent2 = new Intent(MainActivity.this,MediaPlayerActivity.class);
//        Intent intent2 = getPackageManager().getLaunchIntentForPackage("com.dk.viedeoplayer");
//        intent2.putExtra("urlPaths",urlVideoArrays);
//        startActivity(intent2);
//        String[] s={"aa","bb"};
//        int t=1/0;
//        Log.e("TAG", "----------->>>onClick: t= "+t);
//
//        String p=null;
//        for(int i=0;i<3;i++){
//            p+=s[i];
//        }
//        Log.e("TAG", "----------->>>onClick: p= "+p);

//        try {
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    private void hideActionBar() {
        // Hide UI
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}
