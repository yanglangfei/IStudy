package com.yf.istudy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yf.istudy.R;
import com.yf.istudy.util.AppManager;

/**
 * Created by Administrator on 2016/11/14.
 */
public class AdActivity extends Activity implements View.OnClickListener {
    private RelativeLayout mAd1;
    private RelativeLayout mAd2;
    private RelativeLayout mAd3;
    private RelativeLayout mAd4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_ads);
        initView();
    }

    private void initView() {
        AppManager.add(this);
        mAd1 = (RelativeLayout) findViewById(R.id.ad1);
        mAd2 = (RelativeLayout) findViewById(R.id.ad2);
        mAd3 = (RelativeLayout) findViewById(R.id.ad3);
        mAd4 = (RelativeLayout) findViewById(R.id.ad4);
        mAd1.setOnClickListener(this);
        mAd2.setOnClickListener(this);
        mAd3.setOnClickListener(this);
        mAd4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ad1:
                Intent intent=new Intent(this,AdDetail.class);
                intent.putExtra("index",1);
                startActivity(intent);

                break;
            case R.id.ad2:
                Intent inten1t=new Intent(this,AdDetail.class);
                inten1t.putExtra("index",2);
                startActivity(inten1t);
                break;
        }

    }
}
