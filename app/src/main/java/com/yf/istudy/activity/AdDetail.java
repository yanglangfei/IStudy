package com.yf.istudy.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yf.istudy.R;
import com.yf.istudy.util.AppManager;
import com.yf.istudy.util.DataCleanManager;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/14.
 */
public class AdDetail extends Activity{
    private TextView mTvIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_detail);
        mTvIndex = (TextView) findViewById(R.id.tvIndex);
        mTvIndex.setText("广告："+getIntent().getIntExtra("index",0));
        AppManager.add(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DataCleanManager.cleanExternalCache(AdDetail.this);
                DataCleanManager.cleanDatabases(AdDetail.this);
                DataCleanManager.cleanFiles(AdDetail.this);
                DataCleanManager.cleanInternalCache(AdDetail.this);
                DataCleanManager.cleanSharedPreference(AdDetail.this);
                DataCleanManager.cleanApplicationData(AdDetail.this,getCacheDir().getAbsolutePath(),getFilesDir().getAbsolutePath());
                AppManager.finish();
            }
        },1000*5);

    }
}
