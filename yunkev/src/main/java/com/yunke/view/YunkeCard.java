package com.yunke.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunke.entity.YunkeEntity;


/**
 * Created by ChinaNetPro on 2016/11/2.
 */

public abstract class YunkeCard<T extends YunkeEntity> extends RecyclerView.ViewHolder implements View.OnClickListener{
    protected static LayoutInflater mInflater = LayoutInflater.from(YunkeView.getInstance().getContext());
    protected T mEntity;
    protected Activity mContext;

    public YunkeCard(ViewGroup parent , Context context) {
        super(null);
    }

    public YunkeCard(View itemView , Activity context) {
        super(itemView);
        this.mContext = context;
        itemView.setOnClickListener(this);
    }

    public YunkeCard(int resId, ViewGroup parent, Activity context) {
        super(mInflater.inflate(resId ,parent ,false));
        this.mContext = context;
        itemView.setOnClickListener(this);
    }


    protected void bind(T t) {
        this.mEntity = t;
    }

    public T getEntity(){
        return mEntity;
    }

    @Override
    public void onClick(View v) {

    }
}
