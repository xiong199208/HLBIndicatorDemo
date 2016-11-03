package com.xiong.hlbindicatordemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 *  @项目名：  HLBIndicatorDemo 
 *  @包名：    com.xiong.hlbindicatordemo
 *  @文件名:   AudioFragment
 *  @创建者:   ${huanglibo}
 *  @创建时间: 2016/11/1 23:31
 */

public class AudioFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(container.getContext());
        textView.setText("音乐界面");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
