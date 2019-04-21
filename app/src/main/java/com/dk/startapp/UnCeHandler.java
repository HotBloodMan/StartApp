package com.dk.startapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;

public class UnCeHandler implements UncaughtExceptionHandler {

	private UncaughtExceptionHandler mDefaultHandler;
	public final String TAG = "UnCeHandler";
	DemoApplication application;

	public UnCeHandler(DemoApplication application) {
		Log.e(TAG, "------>>>UnCeHandler: ");
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		this.application = application;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e(TAG, "------>>>uncaughtException: " );
		if (!handleException(ex) && mDefaultHandler != null) {
			Log.e(TAG, "------>>>uncaughtException: 111111" );
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			Log.e(TAG, "------>>>uncaughtException: 22222" );
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				Log.e(TAG, "error : ", e);
//			}
//			Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
//			@SuppressLint("WrongConstant") PendingIntent restartIntent = PendingIntent.getActivity(
//					application.getApplicationContext(), 0, intent,
//					Intent.FLAG_ACTIVITY_NEW_TASK);
//			// 退出程序
//			AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
//			mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
			Log.e(TAG, "------>>>uncaughtException: 22222 异常信息 ex="+ex.getMessage().toString() );
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Log.e(TAG, "error : ", e);
			}

            application.finishActivity();
//			Log.e(TAG, "------>>>uncaughtException: 333333" );
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 *
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	private boolean handleException(Throwable ex) {
		Log.e(TAG, "------>>>handleException: " );
		if (ex == null) {
			return false;
		}
		// 使用Toast来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
				Looper.loop();
			}
		}.start();
		return true;
	}

}