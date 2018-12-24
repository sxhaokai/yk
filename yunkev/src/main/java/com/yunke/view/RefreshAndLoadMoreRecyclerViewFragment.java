package com.yunke.view;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunke.R;
import com.yunke.entity.YunkeDataEntity;
import com.yunke.entity.YunkeEntity;
import com.yunke.entity.YunkeListEntity;
import com.yunke.net.CB;
import com.yunke.util.L;
import com.yunke.wrapper.HeaderAndFooterWrapper;
import com.yunke.wrapper.LoadMoreWrapper;

import retrofit2.Call;

/**
 * Created by hao.kai on 2017/5/26.
 */

public abstract class RefreshAndLoadMoreRecyclerViewFragment<T extends YunkeDataEntity<YunkeListEntity<E>>, E extends YunkeEntity, F extends YunkeCard> extends YunkeBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRv;
    private LoadMoreWrapper mLoadMoreWrapper;
    private BaseRecyclerAdapter<E, F> mAdapter;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout srl;

    private int start = 1;
    private int num = 10;
    private boolean mEnable = true;
    protected int startPage;

    protected abstract void requestNet(int start, int count, CB<T> callBack);

    protected void loadData() {
        if (!mEnable) {
            return;
        }
        mEnable = false;
        requestNet(start, num, new CB<T>() {
            @Override
            public void onSuccess(T body) {
                if (null != body.getData().getDataDataList()) {
                    mAdapter.addData(body.getData().getDataDataList());
                    mLoadMoreWrapper.notifyAddData(body.getData().getDataDataList().size());
                    if (body.getData().getDataDataList().size() > 0) {

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

    protected abstract F createCard(ViewGroup parent, int viewType, Activity c);


    private void more() {
        if (needLoadMore()) {
//需要分页加载数据
            loadData();
        } else {
//不需要加载
            mLoadMoreWrapper.notifyAddData(0);
        }
    }


    private void refresh() {
        if (!mEnable) {
            return;
        }
        start = startPage;
        mEnable = false;
        srl.setRefreshing(true);
        L.e("开始刷新");
        requestNet(start, num, new CB<T>() {
            @Override
            public void onSuccess(T body) {
                if (null != body.getData().getDataDataList()) {
                    L.e("刷新的数据 个数： " + body.getData().getDataDataList().size());
                    mAdapter.setData(body.getData().getDataDataList());
                    mLoadMoreWrapper.notifyRefresh();
                    if (body.getData().getDataDataList().size() > 0) {

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

    protected void setStart() {

    }

    public boolean needLoadMore(){
        return true;
    };

    @Override
    public View initView() {
        ViewGroup view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.fragment_refresh_loadmore, null);
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mAdapter = new BaseRecyclerAdapter<E, F>(getContext()) {
            @Override
            protected F provideCard(ViewGroup parent, int viewType) {
                return createCard(parent, viewType, getActivity());
            }
        };

        HeaderAndFooterWrapper<Object> objectHeaderAndFooterWrapper = new HeaderAndFooterWrapper<>(mAdapter);
        if (null != provideHeaderView()) {
            objectHeaderAndFooterWrapper.addHeaderView(provideHeaderView());
        }
        mLoadMoreWrapper = new LoadMoreWrapper(objectHeaderAndFooterWrapper, getContext());
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                more();
            }
        });
        mRv.setAdapter(mLoadMoreWrapper);
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        mRv.addItemDecoration(dividerLine);

        srl.setOnRefreshListener(this);

        startPage = 1;
        setStart();
        return view;
    }

    @Override
    public void initData() {
        refresh();
    }

    public View provideHeaderView(){
        return null;
    }
}
