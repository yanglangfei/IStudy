package com.yf.istudy.service;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.yf.istudy.WelcomActivity;
import com.yf.istudy.activity.MainActivity;

/**
 * Created by Administrator on 2016/11/14.
 */

public class MyService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startKeepLiveService(this,1000*60*30);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
    }

    public void startKeepLiveService(Context context, int timeMillis) {
        //获取AlarmManager系统服务
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //包装Intent
        Intent intent = new Intent(context,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //添加到AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),timeMillis,pendingIntent);
    }

    private boolean isRunningForeground (Context context)
    {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if(!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(getPackageName()))
        {
            return true ;
        }

        return false ;
    }
}
