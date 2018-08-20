package com.yunke.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class YunkeBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifeStyle().onCreateBefore(this ,savedInstanceState);
        //设置主题
        setSelfTheme();
        setContentView(setContentId());
        initView();
        refresh();
    }

    protected abstract int setContentId();

    protected abstract void initView();

    protected abstract void refresh();

    /**
     * 设置自己的主题
     */
    protected void setSelfTheme() {

    }
    private ActivityLifestyle getLifeStyle() {
        return YunkeView.getInstance().getmAc();
    }

    @Override
    protected void onResume() {
        getLifeStyle().onResumeBefore(this);
        super.onResume();
        getLifeStyle().onResumeAfter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifeStyle().onStartAfter(this);
    }

    @Override
    protected void onPause() {
        getLifeStyle().onPauseBefore(this);
        super.onPause();
        getLifeStyle().onPauseAfter(this);
    }

    @Override
    protected void onDestroy() {
        getLifeStyle().onDestroyBefore(this);
        super.onDestroy();
    }
}
