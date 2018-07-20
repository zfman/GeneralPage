package com.zhuangfei.generalpage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Liu ZhuangFei on 2018/2/8.
 * 一个基类Activity
 */

public class BaseActivity extends AppCompatActivity implements OnViewHelperListener {

    private ViewHelper mViewHelper;

    private View getContentView() {
        return ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    }

    public ViewHelper getViewHelper() {
        View view=null;
        if ((view=getContentView())!=null){
            if(mViewHelper==null){
                mViewHelper = new ViewHelper(view);
                mViewHelper.setOnViewHelperListener(this);
            }
            return mViewHelper;
        }

        return null;
    }

    @Override
    public void onLoading() {

    }
}
