package com.yunke.view;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;

/**
 * Created by haokai on 2018/8/20.
 */

public abstract class ActivityLifestyle {
    void onCreateBefore(Activity activity, Bundle savedInstanceState) {
    }

    void onResumeBefore(Activity activity) {
    }

    void onResumeAfter(Activity activity) {
    }

    void onStartAfter(Activity activity) {
    }

    void onPauseBefore(Activity activity) {
    }

    void onPauseAfter(Activity activity) {
    }

    void onDestroyBefore(Activity activity) {
    }
}
