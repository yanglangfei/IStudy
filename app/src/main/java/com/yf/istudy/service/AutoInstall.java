package com.yf.istudy.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.yf.istudy.util.AppManager;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class AutoInstall extends AccessibilityService {
    private  static  final  String TAGPACKAGE="com.yf.istudy";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int  type=event.getEventType();
        switch (type){
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                CharSequence pm = event.getPackageName();
                if(TAGPACKAGE.equals(pm)){
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                        try {
                            click();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

    private void click() throws InterruptedException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            otherMeth(getRootInActiveWindow());
            otherId(getRootInActiveWindow());
        }
    }

    private void otherMeth(AccessibilityNodeInfo view) throws InterruptedException {
        List<AccessibilityNodeInfo> widget = view.findAccessibilityNodeInfosByText("More");
        if(widget.size()>0){
            Thread.sleep(1000*2);
            for(AccessibilityNodeInfo info : widget){
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }

    }

    private void otherId(AccessibilityNodeInfo view) throws InterruptedException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> widget = view.findAccessibilityNodeInfosByViewId("com.yf.istudy:id/ad1");
            if(widget.size()>0){
                Thread.sleep(1000*1);
                for(AccessibilityNodeInfo info : widget){
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }
    @Override
    public void onInterrupt() {

    }

}
