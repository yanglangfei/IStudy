package com.yf.istudy.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class AppManager {
    private static List<Activity> mActivities=new ArrayList<>();
    public  static  void add(Activity activity){
        if(!mActivities.contains(activity)){
           mActivities.add(activity);
        }
    }

    public  static  void  finish(){
        for (Activity activity : mActivities) {
            activity.finish();
        }
    }

}
