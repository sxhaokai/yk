package com.yunke.view;

import android.content.Context;
import android.view.ViewGroup;

import com.yunke.R;
import com.yunke.entity.YunkeEntity;

/**
 * Created by haokai on 2018/8/15.
 */

public class SampCard extends YunkeCard<YunkeEntity> {


    public SampCard(ViewGroup parent, Context context) {
        super(R.layout.item_load_more,parent, context);
    }

    @Override
    public void bind(YunkeEntity yunkeEntity) {

    }
}
