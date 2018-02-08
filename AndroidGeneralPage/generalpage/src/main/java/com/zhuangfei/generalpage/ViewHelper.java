package com.zhuangfei.generalpage;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Liu ZhuangFei on 2018/2/6.
 */

public class ViewHelper implements View.OnClickListener {

    private LayoutInflater mInflate;
    private View mView;

    private LinearLayout loadingLayout;
    private LinearLayout errorLayout;
    private LinearLayout emptyLayout;
    private LinearLayout containerLayout;

    private TextView errorTextView;

    private OnViewHelperListener onViewHelperListener;

    public ViewHelper(View view) {
        mView=view;
        inits();
    }

    public void setOnViewHelperListener(OnViewHelperListener onViewHelperListener) {
        this.onViewHelperListener = onViewHelperListener;
    }

    private void inits() {
        loadingLayout = mView.findViewById(R.id.layout_loading);
        errorLayout = mView.findViewById(R.id.layout_error);
        emptyLayout = mView.findViewById(R.id.layout_empty);
        containerLayout=mView.findViewById(R.id.helper_container);

        errorTextView=mView.findViewById(R.id.textview_error);

        if(check()){
            emptyLayout.setOnClickListener(this);
            errorLayout.setOnClickListener(this);
        }
    }

    public void showLoadingView() {
        if (check()) {
            hideAllViews();
            containerLayout.setVisibility(View.VISIBLE);
            mView.setVisibility(View.VISIBLE);
            loadingLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showEmptyView() {
        if (check()) {
            hideAllViews();
            containerLayout.setVisibility(View.VISIBLE);
            mView.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showErrorView(String msg) {
        if (check()) {
            hideAllViews();
            errorTextView.setText(msg);
            containerLayout.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showSuccessView() {
        if (check()) {
            containerLayout.setVisibility(View.GONE);
        }
    }

    private void hideAllViews() {
        loadingLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
    }

    private boolean check() {
        if (loadingLayout == null || errorLayout == null||emptyLayout==null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.layout_error||id==R.id.layout_empty) {
            if (onViewHelperListener != null) {
                showLoadingView();
                onViewHelperListener.onLoading();
            }
        }
    }
}
