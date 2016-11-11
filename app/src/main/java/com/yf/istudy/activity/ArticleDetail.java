package com.yf.istudy.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.yf.istudy.R;
import com.yf.istudy.model.Article;
import com.yf.istudy.util.Constant;
import com.yf.istudy.util.JsoupUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ArticleDetail extends Activity {
    private TextView mDetailTitle;
    private WebView mBookDetailBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_article_detail);
        initView();
    }

    private void initView() {
        mDetailTitle = (TextView) findViewById(R.id.detailTitle);
        mBookDetailBody = (WebView) findViewById(R.id.bookDetailBody);
        final WebSettings setting = mBookDetailBody.getSettings();
        setting.setBlockNetworkImage(true);
        setting.setJavaScriptEnabled(true);
        mBookDetailBody.setWebChromeClient(new WebChromeClient());
        mBookDetailBody.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                setting.setBlockNetworkImage(false);
            }
        });
        String link=getIntent().getStringExtra("link");
        new GetPsTask().execute(link);
    }

    class  GetPsTask extends AsyncTask<String,Void,Article> {

        @Override
        protected Article doInBackground(String... params) {
            try {
                Document doc = Jsoup.connect(params[0]).get();
                    return  JsoupUtils.getArticleDetail(doc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(Article articles) {
            super.onPostExecute(articles);
            mDetailTitle.setText(articles.getTitle());
            mBookDetailBody.loadDataWithBaseURL(null,articles.getBody(),"text/html","utf-8",null);

        }
    }
}
