package com.zhuangfei.generalpagedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuangfei.generalpage.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Liu ZhuangFei on 2018/2/4.
 */

public class TabFragment1 extends BaseFragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab1, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inits();
    }

    private void inits() {
        getViewHelper().showLoadingView();
    }

}
