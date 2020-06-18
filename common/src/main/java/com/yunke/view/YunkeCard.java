package com.yunke.view;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunke.entity.YunkeEntity;


/**
 * Created by haokai on 2016/11/2.
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
        if (null == this.mEntity) {
            return;
        }
        bindData(this.mEntity);
    }

    @Override
    public void onClick(View v) {
        if (v == itemView) {
            if (null == mEntity) {
                return;
            }
            onItemClick(v ,mEntity);
        }
    }

    /**
     * recyclerView 条目点击 onClick()中已经做了非空判断
     * @param v
     * @param mEntity
     */
    protected abstract void onItemClick(View v, T mEntity);

    /**
     * 绑定数据 bind(T) 里面已经做了null 判断 这个里面不用做
     * @param t
     */
    protected abstract void bindData(T t);

    public T getEntity(){
        return mEntity;
    }
}
