package com.yunke.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunke.R;
import com.yunke.entity.YunkeEntity;
import com.yunke.entity.YunkeListEntity;

import java.util.zip.Inflater;


/**
 * @author Administrator
 */
public class TitlebarUI extends FrameLayout implements OnClickListener {

    private TitleBarClickListner mListner;
    private TextView tvTitle;
    private TextView tvRight;

    public TitlebarUI(@NonNull Context context) {
        this(context ,null);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public TitlebarUI(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.view_title_bar ,this);

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

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_left) {
            if (null != this.mListner) {
                this.mListner.onBackRequst(v);
            }
        }
        if (null != this.mListner) {
            this.mListner.onClickTitleBar(v);
        }
    }

    interface TitleBarClickListner {
        void onClickTitleBar(View v);
        void onBackRequst(View v);
    }

}
