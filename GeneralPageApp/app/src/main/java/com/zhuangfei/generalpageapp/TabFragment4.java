package com.zhuangfei.generalpageapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class TabFragment4 extends BaseFragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab4, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inits();
    }

    private void inits() {
        getViewHelper().showLoadingView();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x000);
            }
        },1000);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            getViewHelper().showSuccessView();
        }
    };
}
