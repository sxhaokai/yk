package com.yunke.net;

import android.app.Application;

import java.util.Objects;
import java.util.function.Supplier;

import okhttp3.Interceptor;

/**
 * Created by haokai on 2018/8/14.
 */

public class YunkeNet {

    private Supplier<Application> mContextSupplier;
    private static final YunkeNet ourInstance = new YunkeNet();
    private String mHost;
    private Interceptor mInterceptor;
    private CallBackHandler mCallBackHandler;

    public static YunkeNet getInstance() {
        return ourInstance;
    }

    private YunkeNet() {
    }

    public void init(Supplier<Application> contextSupplier ,String host ,Interceptor interceptor ,CallBackHandler handler) {
        mContextSupplier = contextSupplier;
        mHost = host;
        mInterceptor = interceptor;
        mCallBackHandler = handler;
    }

    public Application getContext() {
        Objects.requireNonNull(mContextSupplier);
        return mContextSupplier.get();
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
