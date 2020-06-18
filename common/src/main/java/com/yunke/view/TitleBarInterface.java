package com.yunke.view;

import android.app.Activity;
import android.view.View;

/**
 * Created by hao.kai on 2017/6/28.
 */

public interface TitleBarInterface {
    void setTitle(Activity activity, String title);

    void setTitleClickListener(TitlebarUI.TitleBarClickListner listener);

    View selfView();
}
