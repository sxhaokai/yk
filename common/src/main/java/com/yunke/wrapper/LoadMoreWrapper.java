package com.yunke.wrapper;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by zhy on 16/6/23.
 */
public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    private TextView tvNoData;
    private LinearLayout llLoading;

    private RecyclerView.Adapter mInnerAdapter;
    private LoadMoreView mLoadMoreView;
    private int mLoadMoreLayoutId;
    private boolean mNeedFooter = true;

    public LoadMoreWrapper(RecyclerView.Adapter adapter, Context c) {
        mInnerAdapter = adapter;

        mLoadMoreView = new LoadMoreView(c);
        mLoadMoreView.setNeeded(this.mNeedFooter);

    }

    private boolean hasLoadMore() {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0;
    }


    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }

    private boolean isShowLastItem(int position) {
        return position == (mInnerAdapter.getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder;
            if (mLoadMoreView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            if (mLoadMoreView != null) {
                if (mInnerAdapter.getItemCount() == 0) {
                    mLoadMoreView.setVisibility(View.INVISIBLE);
                    return;
                } else {
                    mLoadMoreView.setVisibility(View.VISIBLE);
                }
            }
            if (mOnLoadMoreListener != null) {
                if (mLoadMoreView != null) {
                    mLoadMoreView.setLoading();
                }
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    public int getRealItemCount(){
        return mInnerAdapter.getItemCount();
    }

    public void notifyNoAnyData() {
        mLoadMoreView.dismiss();
    }

    public void notifyNetUnable() {
        //网络不可用
        mLoadMoreView.setNetUnable();
    }

    public void setNeedFooter(boolean b) {
        this.mNeedFooter = b;
        mLoadMoreView.setNeeded(this.mNeedFooter);
    }


    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }



    public void notifyAddData(int count) {
        if (count == 0) {
            noDataAnymore();
            if(getRealItemCount() == 0) {
                //没有数据
                notifyNoAnyData();
            }
            return;
        }
        notifyItemRangeInserted(mInnerAdapter.getItemCount(), count);
    }

    private void noDataAnymore() {
        mLoadMoreView.setNoData();

    }

    public void notifyRefresh() {
        notifyDataSetChanged();
    }

    public void notifyFirstInsert() {
        mInnerAdapter.notifyItemInserted(0);
    }


}
