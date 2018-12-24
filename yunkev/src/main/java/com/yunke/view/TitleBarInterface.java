package com.yunke.view;

import android.app.Activity;
import android.view.View;

/**
 * Created by hao.kai on 2017/6/28.
 */

public interface TitleBarInterface {
    void setTitle(String title);

    void bindActivity(Activity activity);

    void setTitleClickListener(TitlebarUI.TitleBarClickListner listener);

    View selfView();
}
