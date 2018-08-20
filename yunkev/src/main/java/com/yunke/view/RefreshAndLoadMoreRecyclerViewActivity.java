package com.yunke.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yunke.R;
import com.yunke.entity.YunkeEntity;
import com.yunke.entity.YunkeListEntity;
import com.yunke.net.CB2;
import com.yunke.net.CallBack;
import com.yunke.wrapper.LoadMoreWrapper;

import retrofit2.Call;

/**
 * 可配置的方法：
 * 1.{@link #needFooter() 是否需要添加加载更多的脚}
 * 2.{@link #addInitView() 追加初始化控件}
 * 3.{@link #setStart() 设置分页的起始}
 * 3.{@link #needDecration() 是否需要分隔Decration}
 * 3.{@link #provideDecoration() 提供自定义的Decration}
 * Created by hao.kai on 2017/5/26.
 */

public abstract class RefreshAndLoadMoreRecyclerViewActivity<T extends YunkeListEntity<E>, E extends YunkeEntity, F extends YunkeCard> extends YunkeBaseActivity implements TitlebarListener, SwipeRefreshLayout.OnRefreshListener, TitlebarUI.TitleBarClickListner {

    protected RecyclerView mRv;
//    protected TitlebarUI mTitleBar;
    private LoadMoreWrapper mLoadMoreWrapper;
    private BaseRecyclerAdapter<E, F> mAdapter;
    private LinearLayoutManager layoutManager;
    protected SwipeRefreshLayout srl;
    protected int startPage;

    @Override
    protected int setContentId() {
        return R.layout.activity_refresh_loadmore;
    }

    @Override
    protected void initListner() {
        srl.setOnRefreshListener(this);
    }

    private int start = 1;
    private int num = 10;
    private boolean mEnable = true;

    protected abstract void requestNet(int start, int count, CB2<T> callBack);

    protected void loadData() {
        if (!mEnable) {
            return;
        }
        mEnable = false;
        requestNet(start, num, new CB2<T>() {
            @Override
            public void onSuccess(T body) {
                if (null != body.getDataDataList()) {
                    mAdapter.addData(body.getDataDataList());
                    mLoadMoreWrapper.notifyAddData(body.getDataDataList().size());
                    if (body.getDataDataList().size() > 0) {

                        start++;
                    }
                }
                mEnable = true;


            }

            @Override
            public void netFail(Call call, String failMessage) {
                super.netFail(call, failMessage);
                mLoadMoreWrapper.notifyNetUnable();
                mEnable = true;
            }

            @Override
            public void onError(T body) {
                super.onError(body);
                mLoadMoreWrapper.notifyNetUnable();
                mEnable = true;
            }
        });

    }


    @Override
    protected void initView() {
        super.initView();
            mRv = (RecyclerView) findViewById(R.id.rv);
            srl = (SwipeRefreshLayout) findViewById(R.id.srl);
//        mTitleBar = (TitlebarUI) findViewById(R.id.status_bar_title_bar);
//        mTitleBar.setListener(this);
//        mTitleBar.setTitle(provideTitle());

            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRv.setLayoutManager(layoutManager);
            mAdapter = new BaseRecyclerAdapter<E, F>(this) {
                @Override
                protected F provideCard(ViewGroup parent, int viewType) {
                    return createCard(parent, viewType, RefreshAndLoadMoreRecyclerViewActivity.this);
                }
            };

            mLoadMoreWrapper = new LoadMoreWrapper(mAdapter, this);
            mLoadMoreWrapper.setNeedFooter(needFooter());
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    more();
                }
            });
            mRv.setAdapter(mLoadMoreWrapper);
            if (needDecration()) {
                mRv.addItemDecoration(provideDecoration());
            }

            startPage = 1;
            setStart();
            addInitView();

    }

    /**
     * 是否需要加载更多时候的脚
     *
     * @return
     */
    protected boolean needFooter() {
        return true;
    }

    protected RecyclerView.ItemDecoration provideDecoration() {
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        return dividerLine;
    }

    protected boolean needDecration() {
        return true;
    }

    /**
     * 追加初始化
     */
    protected void addInitView() {

    }

    /**
     * 设置分页start的值
     */
    protected void setStart() {

    }

    protected abstract String provideTitle();

    protected abstract F createCard(ViewGroup parent, int viewType, Context c);


    private void more() {
        if (needLoadMore()) {
//需要分页加载数据
            loadData();
        } else {
//不需要加载
            mLoadMoreWrapper.notifyAddData(0);
        }
    }

    @Override
    public void zuobian(View v) {
        back();

    }

    private void back() {
        finish();
    }


    @Override
    public void zhongjian(View v) {

    }

    @Override
    public void youbian(View v) {
    }


    @Override
    protected void refresh() {
        if (!mEnable) {
            return;
        }
        start = startPage;
        mEnable = false;
        srl.setRefreshing(true);
        requestNet(start, num, new CB2<T>() {
            @Override
            public void onSuccess(T body) {
                if (null != body.getDataDataList()) {
                    mAdapter.setData(body.getDataDataList());
                    mLoadMoreWrapper.notifyRefresh();
                    if (body.getDataDataList().size() > 0) {

                        start++;
                    }
                }
                mEnable = true;
                srl.setRefreshing(false);
            }

            @Override
            public void netFail(Call call, String failMessage) {
                super.netFail(call, failMessage);
                mLoadMoreWrapper.notifyNetUnable();
                mEnable = true;
                srl.setRefreshing(false);
            }

            @Override
            public void onError(T body) {
                super.onError(body);
                mLoadMoreWrapper.notifyNetUnable();
                mEnable = true;
                srl.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public abstract boolean needLoadMore();

    @Override
    public void onClick(View v) {

    }
}
