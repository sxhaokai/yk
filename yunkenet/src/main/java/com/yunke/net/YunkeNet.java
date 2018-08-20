package com.yunke.net;

import android.content.Context;

import java.util.Objects;

import okhttp3.Interceptor;

/**
 * Created by haokai on 2018/8/14.
 */

public class YunkeNet {

    private Context mContext;
    private static final YunkeNet ourInstance = new YunkeNet();
    private String mHost;
    private Interceptor mInterceptor;
    private CallBackHandler mCallBackHandler;

    public static YunkeNet getInstance() {
        return ourInstance;
    }

    private YunkeNet() {
    }

    public void init(Context context,String host ,Interceptor interceptor ,CallBackHandler handler) {
        mContext = context;
        mHost = host;
        mInterceptor = interceptor;
        mCallBackHandler = handler;
    }

    public Context getContext() {
        Objects.requireNonNull(mContext);
        return mContext;
    }

    public CallBackHandler getmCallBackHandler() {
        return mCallBackHandler;
    }

    public String getmHost() {
        return mHost;
    }


    public Interceptor getmInterceptor() {


        return mInterceptor;
    }
}
