package com.yunke.view;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;

/**
 * Created by haokai on 2018/8/20.
 */

public abstract class ActivityLifestyle {
    public void onCreateBefore(YunkeBaseActivity activity, Bundle savedInstanceState) {
    }

    public void onResumeBefore(YunkeBaseActivity activity) {
    }

    public void onResumeAfter(YunkeBaseActivity activity) {
    }

    public void onStartAfter(YunkeBaseActivity activity) {
    }

    public void onPauseBefore(YunkeBaseActivity activity) {
    }

    public void onPauseAfter(YunkeBaseActivity activity) {
    }

    public void onDestroyBefore(YunkeBaseActivity activity) {
    }
}
