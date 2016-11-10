package com.yf.istudy.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.yf.istudy.R;
/**
 * Created by Administrator on 2016/11/10.
 */
public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    private  View current;
    private RadioButton mArticle;
    private RadioButton mVideo;
    private RadioButton mMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        initView();

    }

    private void initView() {
        mArticle = (RadioButton) findViewById(R.id.article);
        mVideo = (RadioButton) findViewById(R.id.video);
        mMore = (RadioButton) findViewById(R.id.more);

        mArticle.setOnCheckedChangeListener(this);
        mVideo.setOnCheckedChangeListener(this);
        mMore.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.article:

                break;
            case  R.id.video:
                break;
            case R.id.more:
                break;
            default:
                break;
        }

    }

    public  void  changeCheck(View view){
        if(current!=null&&current.getId()!=view.getId()){
            current.setEnabled(true);
        }

        view.setEnabled(false);
        current=view;

    }


}
