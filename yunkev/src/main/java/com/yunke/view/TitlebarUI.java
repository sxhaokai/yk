package com.yunke.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yunke.R;


/**
 * @author Administrator
 */
public class TitlebarUI extends FrameLayout implements OnClickListener {

    private TitleBarClickListner mListner;
    private TextView tvTitle;
    private TextView tvRight;
    private Activity mActivity;

    public TitlebarUI(@NonNull Context context) {
        this(context, null);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);

        findViewById(R.id.iv_left).setOnClickListener(this);
    }

    public void setListener(TitleBarClickListner listener) {

        this.mListner = listener;
    }

    public void provideTitle(String title) {
        tvTitle.setText(title);
    }

    public void setRightText(String rightText) {
        tvRight.setText(rightText);
    }

    public void bindActivity(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_left) {
            if (null != mActivity) {
                mActivity.finish();
            }
            if (null != this.mListner) {
                this.mListner.onBackRequst(v);
            }
        } else if (i == R.id.tv_right) {
            if (null != this.mListner) {
                this.mListner.onTitleRightTextClick(v);
            }
        }
    }

    interface TitleBarClickListner {
        void onBackRequst(View v);
        void onTitleRightTextClick(View v);
    }

}
