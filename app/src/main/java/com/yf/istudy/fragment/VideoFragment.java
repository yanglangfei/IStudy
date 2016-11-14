package com.yf.istudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yf.istudy.R;

/**
 * Created by Administrator on 2016/11/10.
 */

public class VideoFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ui_video,container,false);
        initView();
        return view;
    }

    private void initView() {

    }
}
