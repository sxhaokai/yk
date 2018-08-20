package com.yunke.wrapper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunke.R;


/**
 * Created by dong.wei on 2017/3/13.
 */

public class LoadMoreView extends FrameLayout {

    private TextView tvNoData;
    private LinearLayout llLoading;
    private ViewGroup mLoadMoreView;
    private TextView tvNoNet;
    private boolean mNeeded = true;

    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLoadMoreView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.item_load_more, null);
        tvNoData = (TextView) mLoadMoreView.findViewById(R.id.tv_no_data);
        tvNoNet = (TextView) mLoadMoreView.findViewById(R.id.tv_no_net);
        llLoading = (LinearLayout) mLoadMoreView.findViewById(R.id.ll_loading);
        addView(mLoadMoreView);
    }

    public void setNoData() {
        show();
        tvNoNet.setVisibility(INVISIBLE);
        llLoading.setVisibility(INVISIBLE);
        tvNoData.setVisibility(VISIBLE);
    }

    public void dismiss() {
        mLoadMoreView.setVisibility(GONE);
    }


    public void show() {
        mLoadMoreView.setVisibility(mNeeded ? VISIBLE : GONE);

    }

    public void setLoading() {
        show();
        tvNoNet.setVisibility(INVISIBLE);
        tvNoData.setVisibility(INVISIBLE);
        llLoading.setVisibility(VISIBLE);
    }

    /**
     * 网络不可用
     */
    public void setNetUnable() {
        show();
        tvNoData.setVisibility(INVISIBLE);
        llLoading.setVisibility(INVISIBLE);
        tvNoNet.setVisibility(VISIBLE);
    }

    public void setNeeded(boolean mNeedFooter) {
        this.mNeeded = mNeedFooter;
    }
}
