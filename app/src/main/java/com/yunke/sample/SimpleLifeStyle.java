package com.yunke.sample;

import android.app.Activity;
import android.os.Bundle;

import com.yunke.view.ActivityLifestyle;

/**
 * Created by haokai on 2018/8/20.
 */

public class SimpleLifeStyle extends ActivityLifestyle{
    @Override
    public void onCreateBefore(Activity activity, Bundle savedInstanceState) {
        super.onCreateBefore(activity, savedInstanceState);
    }

    @Override
    public void onResumeBefore(Activity activity) {
        super.onResumeBefore(activity);
    }

    @Override
    public void onResumeAfter(Activity activity) {
        super.onResumeAfter(activity);
    }

    @Override
    public void onStartAfter(Activity activity) {
        super.onStartAfter(activity);
    }

    @Override
    public void onPauseBefore(Activity activity) {
        super.onPauseBefore(activity);
    }

    @Override
    public void onPauseAfter(Activity activity) {
        super.onPauseAfter(activity);
    }

    @Override
    public void onDestroyBefore(Activity activity) {
        super.onDestroyBefore(activity);
    }
}
