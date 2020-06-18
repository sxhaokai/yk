package com.yunke.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import com.yunke.entity.YunkeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong.wei on 2016/11/30.
 */

public abstract class BaseRecyclerAdapter<T extends YunkeEntity,E extends YunkeCard> extends RecyclerView.Adapter<E> {


    protected final Context mContext;
    private final ArrayList<T> mData;

    protected BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    public void addData(List<T> sata){
        mData.addAll(sata);
    }
    public void setData(List<T> data){
        this.mData.clear();
        this.mData.addAll(data);
    }


    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        return provideCard(parent ,viewType);
    }

    @Override
    public void onBindViewHolder(YunkeCard holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void addData(T e) {
        mData.add(0 ,e);

    }
    protected abstract E provideCard(ViewGroup parent, int viewType);

}
