package com.zhuangfei.generalpageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuangfei.generalpage.BaseActivity;

public class CustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        inits();
    }

    private void inits() {
        //可不写，默认loadingView是开启的
        getViewHelper().showLoadingView();
    }
}
