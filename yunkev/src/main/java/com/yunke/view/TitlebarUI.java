package com.yunke.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.yunke.entity.YunkeEntity;
import com.yunke.entity.YunkeListEntity;


/**
 * @author Administrator
 */
public class TitlebarUI extends FrameLayout {

    public TitlebarUI(@NonNull Context context) {
        super(context);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public <E extends YunkeEntity, F extends YunkeCard> void setTitle(String title) {

    }

    public void setListener(TitleBarClickListner listener) {

    }

    interface TitleBarClickListner {
        void onClick(View v);
    }

}
