package com.yf.istudy.util;

import org.jsoup.safety.Whitelist;

/**
 * Created by Administrator on 2016/11/10.
 *    规则类
 */
public class Rule {

    //请求的URL
    private  String url;
    //请求参数名
    private  String params[];
    //请求参数值
    private  String values[];

    //解析类型    CLASS ID  TAG    SESSION 默认id
    private  int type=ID;
    //解析的参数
    private  String result[];
    //请求方式   默认get
    private  int requestMeth=GET;

    private  static  final  int ID=0;

    private  static  final  int CLASS=1;

    private  static  final  int TAG=2;

    private  static  final  int SELECTION=3;

    private  static  final  int GET=0;
    private  static  final  int POST=1;
    public Rule() {
    }

    public Rule(String url, String[] params, String[] values, int type, String[] result, int requestMeth) {
        this.url = url;
        this.params = params;
        this.values = values;
        this.type = type;
        this.result = result;
        this.requestMeth = requestMeth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    public int getRequestMeth() {
        return requestMeth;
    }

    public void setRequestMeth(int requestMeth) {
        this.requestMeth = requestMeth;
    }
}
