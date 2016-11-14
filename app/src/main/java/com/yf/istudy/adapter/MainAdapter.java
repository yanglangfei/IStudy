package com.yf.istudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yf.istudy.R;
import com.yf.istudy.model.Article;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */
public class MainAdapter extends BaseAdapter {
    private  List<Article> articles;
    private  Context context;

    public MainAdapter(List<Article> articles, Context context) {
        this.articles=articles;
        this.context=context;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.ui_article_item,null);
        }
        ImageView mBookImg = (ImageView) convertView.findViewById(R.id.bookImg);
        TextView mBookTitle = (TextView) convertView.findViewById(R.id.bookTitle);
        TextView mBookSummary = (TextView) convertView.findViewById(R.id.bookSummary);
        Article article=articles.get(position);
        mBookTitle.setText(article.getTitle());
        mBookSummary.setText(article.getSummary());
        Glide.with(context).load(article.getImag()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mBookImg);
        return convertView;
    }
}
