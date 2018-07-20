package com.zhuangfei.generalpagedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuangfei.generalpage.BaseFragment;

/**
 * Created by Liu ZhuangFei on 2018/2/4.
 */

public class TabFragment2 extends BaseFragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab2, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inits();
    }

    private void inits() {
        getViewHelper().showErrorView("网络连接错误");
    }

    @Override
    public void onLoading() {
        Toast.makeText(getActivity(),"重新加载中..",Toast.LENGTH_SHORT).show();
    }
}
