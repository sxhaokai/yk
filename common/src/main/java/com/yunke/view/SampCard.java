package com.yunke.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.yunke.R;
import com.yunke.entity.YunkeEntity;

/**
 * Created by haokai on 2018/8/15.
 */

public class SampCard extends YunkeCard<YunkeEntity> {


    public SampCard(ViewGroup parent, Activity context) {
        super(R.layout.item_load_more,parent, context);
    }

    @Override
    protected void onItemClick(View v, YunkeEntity mEntity) {

    }

    @Override
    protected void bindData(YunkeEntity yunkeEntity) {

    }
}
