package com.yf.istudy;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yf.istudy.activity.ArticleDetail;
import com.yf.istudy.activity.MainActivity;
import com.yf.istudy.adapter.MainAdapter;
import com.yf.istudy.model.Article;
import com.yf.istudy.service.AutoInstall;
import com.yf.istudy.service.MyService;
import com.yf.istudy.util.AppManager;
import com.yf.istudy.util.Constant;
import com.yf.istudy.util.JsoupUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WelcomActivity extends Activity {
    private TextView mWelcom;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        AppManager.add(this);
        Intent myIntent=new Intent(this, AutoInstall.class);
        startService(myIntent);
        mWelcom = (TextView) findViewById(R.id.welcom);
/*

        AnimatorSet set=new AnimatorSet();
        ObjectAnimator animator=ObjectAnimator.ofFloat(mWelcom,"alpha",0,1);
        animator.setDuration(2000);

        ObjectAnimator trans=ObjectAnimator.ofFloat(mWelcom,"translationY",0,100);
         trans.setDuration(2000);

        ObjectAnimator rote=ObjectAnimator.ofFloat(mWelcom,"rotationX",0,45);
          rote.setDuration(2000);


        ObjectAnimator scale=ObjectAnimator.ofFloat(mWelcom,"scaleX",0,10);
        scale.setDuration(2000);


        set.play(animator).after(trans).after(rote).after(scale);
        set.start();
*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             Intent main=new Intent(WelcomActivity.this, MainActivity.class);
                WelcomActivity.this.startActivity(main);
                WelcomActivity.this.finish();
            }
        },1000);
    }


}
