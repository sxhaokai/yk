package com.yunke.view;

import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by hao.kai on 2017/6/21.
 */

public class PTRefresh extends SwipeRefreshLayout {
    public PTRefresh(Context context) {
        this(context ,null);
    }

    public PTRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }
}
