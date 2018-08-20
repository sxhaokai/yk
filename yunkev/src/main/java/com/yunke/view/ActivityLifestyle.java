package com.yunke.view;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;

/**
 * Created by haokai on 2018/8/20.
 */

public abstract class ActivityLifestyle {
    public void onCreateBefore(Activity activity, Bundle savedInstanceState) {
    }

    public void onResumeBefore(Activity activity) {
    }

    public void onResumeAfter(Activity activity) {
    }

    public void onStartAfter(Activity activity) {
    }

    public void onPauseBefore(Activity activity) {
    }

    public void onPauseAfter(Activity activity) {
    }

    public void onDestroyBefore(Activity activity) {
    }
}
