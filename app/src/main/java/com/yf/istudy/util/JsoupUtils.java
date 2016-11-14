package com.yf.istudy.util;

import android.util.Log;

import com.yf.istudy.model.Article;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class JsoupUtils {
    /**
     * @param doc
     * @param articles
     * @return   解析文章列表信息
     */
    public static List<Article> getArticleList(Document doc, List<Article> articles) {
        Elements results = doc.getElementsByClass("wx-rb3");
        for (int i = 0; i < results.size(); i++) {
            Element title=results.get(i).getElementById("sogou_vr_11002601_title_"+i);
            Element a = results.get(i).getElementById("sogou_vr_11002601_summary_"+i);
            Element img=results.get(i).getElementsByTag("img").first();
            Element account=results.get(i).getElementById("weixin_account");
            Article article=new Article();
            article.setTitle(title.text());
            article.setSummary(a.text());
            article.setLink(title.attr("href"));
            article.setFrom(account.attr("title"));
            article.setImag(img.attr("src"));
            articles.add(article);
        }
        return articles;
    }

    /**
     * @param doc
     * @return
     * @auth      解析 文章内容
     */
    public static Article getArticleDetail(Document doc) {
        Element results = doc.getElementsByClass("rich_media_content").first();
        Element rich_media_titl = doc.getElementsByClass("rich_media_title").first();
        Article article=new Article();
        article.setTitle(rich_media_titl.text());
        article.setBody(resetBody(results));
        return article;
    }

    /**
     * @param body
     * @return  格式化内容中的图片
     */
    public static String  resetBody(Element body){
        Elements img = body.getElementsByTag("img");
        for (int i = 0; i < img.size(); i++) {
            String src=img.get(i).attr("data-src");
            img.get(i).attr("style","");
            img.get(i).attr("src",src);
            img.get(i).attr("width","100%");
            img.get(i).attr("height","auto");
        }
        return  body.html();
    }


}
