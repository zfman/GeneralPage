package com.zhuangfei.generalpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Liu ZhuangFei on 2018/2/6.
 */

public class BaseFragment extends Fragment implements OnViewHelperListener{

    View mView;
    private ViewHelper mViewHelper;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mView=view;
        mViewHelper=new ViewHelper(mView);
        mViewHelper.setOnViewHelperListener(this);
    }

    public ViewHelper getViewHelper(){
        return mViewHelper;
    }


    @Override
    public void onLoading() {

    }
}
