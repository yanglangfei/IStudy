package com.yf.istudy.activity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import com.yf.istudy.R;
import com.yf.istudy.fragment.ArticleFragment;
import com.yf.istudy.fragment.MoreFragment;
import com.yf.istudy.fragment.VideoFragment;
import com.yf.istudy.service.MyService;
import com.yf.istudy.util.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
public class MainActivity extends FragmentActivity implements CompoundButton.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private  RadioButton current;
    private RadioButton mArticle;
    private RadioButton mVideo;
    private RadioButton moreRb;
    private ViewPager mPager;
    private Button more;
    private List<Fragment> mFragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        initData();
        initView();

    }

    private void initData() {
        mFragments.clear();
        mFragments.add(new ArticleFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new MoreFragment());
    }

    private void initView() {
        AppManager.add(this);
        Intent service=new Intent(this,MyService.class);
        startService(service);
        mArticle = (RadioButton) findViewById(R.id.article);
        mVideo = (RadioButton) findViewById(R.id.video);
        moreRb = (RadioButton) findViewById(R.id.moreRb);
        mPager= (ViewPager) findViewById(R.id.mainVp);
        more= (Button) findViewById(R.id.more);
        more.setOnClickListener(this);
        mPager.setOnPageChangeListener(this);

        mArticle.setOnCheckedChangeListener(this);
        mVideo.setOnCheckedChangeListener(this);
        moreRb.setOnCheckedChangeListener(this);
        mArticle.setChecked(true);
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              AppManager.finish();
            }
        },3000);*/
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.article:
                if(isChecked){
                    mPager.setCurrentItem(0);
                    mVideo.setChecked(false);
                    moreRb.setChecked(false);
                    mArticle.setTextColor(Color.YELLOW);
                    moreRb.setTextColor(Color.BLACK);
                    mVideo.setTextColor(Color.BLACK);
                }
                break;
            case  R.id.video:
                if(isChecked){
                    mPager.setCurrentItem(1);
                    mArticle.setChecked(false);
                    moreRb.setChecked(false);

                    mVideo.setTextColor(Color.YELLOW);
                    moreRb.setTextColor(Color.BLACK);
                    mArticle.setTextColor(Color.BLACK);
                }
                break;
            case R.id.more:
                if(isChecked){
                    mPager.setCurrentItem(2);
                    mVideo.setChecked(false);
                    mArticle.setChecked(false);

                    moreRb.setTextColor(Color.YELLOW);
                    mVideo.setTextColor(Color.BLACK);
                    mArticle.setTextColor(Color.BLACK);
                }
                break;
            default:
                break;
        }

    }

    public  void  changeCheck(RadioButton view){
        if(current!=null&&current.getId()!=view.getId()){
            current.setChecked(false);
        }
        view.setChecked(true);
        current=view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                changeCheck(mArticle);
                break;
            case 1:
                changeCheck(mVideo);
                break;
            case 2:
                changeCheck(moreRb);
                break;
            default:
                break;
        }



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(this,AdActivity.class);
        startActivity(intent);
    }
}
