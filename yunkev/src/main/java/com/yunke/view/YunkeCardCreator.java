package com.yunke.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

/**
 * Created by hao.kai on 2017/6/29.
 */

public class YunkeCardCreator {

    public static YunkeCard create(Class<? extends YunkeCard> clazzz, ViewGroup parent, int layoutId, Context c) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        try {
            Constructor<? extends YunkeCard> constructor = clazzz.getConstructor(View.class, Context.class);

            return constructor.newInstance(v, c);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
