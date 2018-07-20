package com.zhuangfei.generalpagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuangfei.generalpage.BaseActivity;

public class ExampleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        inits();
    }

    private void inits() {
        //隐藏提示页面
//        getViewHelper().showSuccessView();

        //无数据页面
//        getViewHelper().showEmptyView();

        //异常页面
//        getViewHelper().showErrorView("网络连接出错了!");

        //加载页面
        getViewHelper().showLoadingView();
    }
}
