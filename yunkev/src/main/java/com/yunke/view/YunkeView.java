package com.yunke.view;

import android.app.Application;

import com.yunke.net.YunkeNet;

import java.security.Provider;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by haokai on 2018/8/14.
 */

public class YunkeView {

    private static final YunkeView ourInstance = new YunkeView();

    public static YunkeView getInstance() {
        return ourInstance;
    }

    private YunkeView() {
    }


    public Application getContext() {
        Objects.requireNonNull(YunkeNet.getInstance().getContext());
        return YunkeNet.getInstance().getContext();
    }

}
