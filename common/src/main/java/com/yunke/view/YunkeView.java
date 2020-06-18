package com.yunke.view;

import android.content.Context;

import com.yunke.net.YunkeNet;

import java.util.Objects;

/**
 * Created by haokai on 2018/8/14.
 */

public class YunkeView {

    private static final YunkeView ourInstance = new YunkeView();
    private ActivityLifestyle mAc;

    public static YunkeView getInstance() {
        return ourInstance;
    }

    private YunkeView() {
    }

    public Context getContext() {
        Objects.requireNonNull(YunkeNet.getInstance().getContext());
        Objects.requireNonNull(getmAc());
        return YunkeNet.getInstance().getContext();
    }

    public void addAcitvityLifeStyle(ActivityLifestyle activityLifestyle) {
        this.mAc = activityLifestyle;
    }

    public ActivityLifestyle getmAc() {
        return mAc;
    }
}
