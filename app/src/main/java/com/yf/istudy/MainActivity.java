package com.yf.istudy;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.yf.istudy.activity.ArticleDetail;
import com.yf.istudy.adapter.MainAdapter;
import com.yf.istudy.model.Article;
import com.yf.istudy.util.Constant;
import com.yf.istudy.util.JsoupUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, CompoundButton.OnCheckedChangeListener {
    private ListView mListView;
    private MainAdapter mAdapter;
    private  List<Article> mArticles=new ArrayList<>();
    private  int page=1;
    private View footer;
    private RadioButton mPsTeach;
    private RadioButton mTbTeach;
    private  RadioButton mAeTeach;
    private  RadioButton mHahaTeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        footer= LayoutInflater.from(this).inflate(R.layout.ui_footview,null);
        mListView= (ListView) findViewById(R.id.psList);
        mPsTeach = (RadioButton) findViewById(R.id.psTeach);
        mTbTeach = (RadioButton) findViewById(R.id.tbTeach);
        mAeTeach= (RadioButton) findViewById(R.id.aeTeach);
        mHahaTeach= (RadioButton) findViewById(R.id.hahaTeach);
        mHahaTeach.setOnCheckedChangeListener(this);
        mPsTeach.setOnCheckedChangeListener(this);
        mAeTeach.setOnCheckedChangeListener(this);
        mTbTeach.setOnCheckedChangeListener(this);
        mAdapter=new MainAdapter(mArticles,this);
        mListView.addFooterView(footer);
        mListView.setAdapter(mAdapter);
        mListView.removeFooterView(footer);
        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
        mPsTeach.setChecked(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String link=mArticles.get(position).getLink();
        Intent detail=new Intent();
        detail.setClass(this, ArticleDetail.class);
        detail.putExtra("link",link);
        startActivity(detail);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case  SCROLL_STATE_IDLE:
                // 判断是否滚动到底部
                if (view.getLastVisiblePosition() == view.getCount() - 1&&mArticles.size()>0) {
                    //加载更多功能的代码
                        mListView.addFooterView(footer);
                        ++page;
                    if(mPsTeach.isChecked()){
                        new GetPsTask().execute(page+"","ps");
                    }else if(mTbTeach.isChecked()){
                        new GetPsTask().execute(page+"","taobao");
                    }else if(mAeTeach.isChecked()){
                        new GetPsTask().execute(page+"","AE");
                    }else {
                        new GetPsTask().execute(page+"","hahawaizhuan");
                    }

                }
                break;
            case  SCROLL_STATE_FLING:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.psTeach:
                if(isChecked){
                //PS 教程
                    mArticles.clear();
                    mAdapter.notifyDataSetChanged();
                    mPsTeach.setTextColor(Color.RED);
                    mTbTeach.setChecked(false);
                    mAeTeach.setChecked(false);
                    mHahaTeach.setTextColor(Color.BLACK);
                    mHahaTeach.setChecked(false);
                    mAeTeach.setTextColor(Color.BLACK);
                    mTbTeach.setTextColor(Color.BLACK);
                    new GetPsTask().execute(1+"","ps");
                }
                break;
            case  R.id.tbTeach:
                if(isChecked){
                    //淘宝教程
                    mArticles.clear();
                    mAdapter.notifyDataSetChanged();
                    mPsTeach.setTextColor(Color.BLACK);
                    mAeTeach.setChecked(false);
                    mAeTeach.setTextColor(Color.BLACK);
                    mHahaTeach.setTextColor(Color.BLACK);
                    mHahaTeach.setChecked(false);
                    mTbTeach.setChecked(false);
                    mTbTeach.setTextColor(Color.RED);
                    new GetPsTask().execute(1+"","taobao");
                }
                break;
            case R.id.aeTeach:
                if(isChecked){
                    mArticles.clear();
                    mAdapter.notifyDataSetChanged();
                    mPsTeach.setTextColor(Color.BLACK);
                    mAeTeach.setChecked(true);
                    mAeTeach.setTextColor(Color.RED);
                    mHahaTeach.setTextColor(Color.BLACK);
                    mHahaTeach.setChecked(false);
                    mTbTeach.setChecked(false);
                    mTbTeach.setTextColor(Color.BLACK);
                    new GetPsTask().execute(1+"","AE");
                }
                break;
            case R.id.hahaTeach:
                mArticles.clear();
                mAdapter.notifyDataSetChanged();
                mPsTeach.setTextColor(Color.BLACK);
                mAeTeach.setChecked(false);
                mAeTeach.setTextColor(Color.BLACK);
                mHahaTeach.setTextColor(Color.RED);
                mHahaTeach.setChecked(true);
                mTbTeach.setChecked(false);
                mTbTeach.setTextColor(Color.BLACK);
                new GetPsTask().execute(1+"","hahawaizhuan");
                break;
        }

    }


    class  GetPsTask extends AsyncTask<String,Void,List<Article>>{

        @Override
        protected List<Article> doInBackground(String... params) {
            try {
                String url = Constant.GETPS.replace("{n}", params[0]).replace("{m}", params[1]);
                Document doc = Jsoup.connect(url)
                        .get();
                return  JsoupUtils.getArticleList(doc,mArticles);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
             mArticles=articles;
             mAdapter.setArticles(articles);
            mAdapter.notifyDataSetChanged();
        }
    }
}
