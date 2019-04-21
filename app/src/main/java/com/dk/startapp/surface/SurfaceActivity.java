package com.dk.startapp.surface;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.UriPermission;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.dk.startapp.R;

public class SurfaceActivity extends AppCompatActivity implements View.OnClickListener {

  public static String TAG="SurfaceActivity";
    Button btnVideoPath;
    private  Context mContext;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MyView(this));
        setContentView(R.layout.activity_surface);
        btnVideoPath = findViewById(R.id.btn_surface);
        btnVideoPath.setOnClickListener(this);
//        mContext=this;

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.MANAGE_DOCUMENTS};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }


    }

    @Override
    public void onClick(View v) {
//        data=content://com.android.providers.media.documents/document/video%3A218
        Uri uri = Uri.parse("content://com.android.providers.media.documents/document/video%3A218");
        Log.d(TAG,"------------>>>url= "+uri);
        String path = getPath(mContext, uri);
        Log.d(TAG,"------------>>>path= "+path);


//        ContentResolver cr = this.getContentResolver();
//        Cursor cursor = cr.query(uri, null, null, null, null);
//        try {
//            if (cursor.moveToFirst()) {
//                // 视频ID:MediaStore.Audio.Media._ID
//                int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
//                Log.d(TAG,"-------->>>videoId= "+videoId);
//                // 视频名称：MediaStore.Audio.Media.TITLE
//                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
//                Log.d(TAG,"-------->>>title= "+title);
//                // 视频路径：MediaStore.Audio.Media.DATA
//                String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
//                Log.d(TAG,"------>>>videoPath= "+videoPath);
//                // 视频时长：MediaStore.Audio.Media.DURATION
//                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
//                // 视频大小：MediaStore.Audio.Media.SIZE
//                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
//
//                // 视频缩略图路径：MediaStore.Images.Media.DATA
//                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//                // 缩略图ID:MediaStore.Audio.Media._ID
//                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
//
//                cursor.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }


        class MyView extends SurfaceView implements SurfaceHolder.Callback {

            private SurfaceHolder holder;
            private MyThread myThread;

            public MyView(Context context) {
                super(context);
                holder = this.getHolder();
                holder.addCallback(this);
                myThread = new MyThread(holder);
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.isRun = true;
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                myThread.isRun = false;
            }
        }

        class MyThread extends Thread {
            private SurfaceHolder holder;
            public boolean isRun;

            public MyThread(SurfaceHolder holder) {
                this.holder = holder;
                isRun = true;
            }

            @Override
            public void run() {
                int count = 0;
                while (isRun) {
                    Canvas c = null;
                    try {
                        synchronized (holder) {
                            c = holder.lockCanvas();
                            c.drawColor(Color.BLACK);
                            Paint p = new Paint();
                            p.setColor(Color.BLACK);
                            Rect r = new Rect(100, 50, 300, 250);
                            c.drawRect(r, p);
                            c.drawText("这是第" + (count++) + "秒", 100, 310, p);
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (c != null) {
                            holder.unlockCanvasAndPost(c);
                        }
                    }
                }

            }
        }



    public  String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {


         ContentResolver cr = getContentResolver();
            Log.d(TAG,"-------->>>cr= "+cr);
          Cursor cursor = cr.query(uri, null, null, null, null);
            Log.d(TAG,"-------->>>cursor= "+cursor);
            boolean b = cursor.moveToFirst();
            Log.d(TAG,"-------->>>b= "+b);

//        try {
//            if (cursor.moveToFirst()) {
//                // 视频ID:MediaStore.Audio.Media._ID
//                int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
//                Log.d(TAG,"-------->>>videoId= "+videoId);
//                // 视频名称：MediaStore.Audio.Media.TITLE
//                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
//                Log.d(TAG,"-------->>>title= "+title);
//                // 视频路径：MediaStore.Audio.Media.DATA
//                String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
//                Log.d(TAG,"------>>>videoPath= "+videoPath);
//                // 视频时长：MediaStore.Audio.Media.DURATION
//                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
//                // 视频大小：MediaStore.Audio.Media.SIZE
//                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
//
//                // 视频缩略图路径：MediaStore.Images.Media.DATA
//                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//                // 缩略图ID:MediaStore.Audio.Media._ID
//                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));

//                cursor.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }


            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                Log.d(TAG,"----111-->>>");

                final String docId = DocumentsContract.getDocumentId(uri);
                Log.d(TAG,"------->>>docId= "+docId);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                Log.d(TAG,"----222-->>>");

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                Log.d(TAG,"------->>>contentUri= "+contentUri);
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                Log.d(TAG,"----333-->>>");

                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                Log.d(TAG,"----333-->>>contentUri= "+contentUri);
                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            Log.d(TAG,"---444--->>>");
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            Log.d(TAG,"---555--->>>");
            return uri.getPath();
        }
        return null;
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}