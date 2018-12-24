package com.yunke.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.yunke.R;


/**
 * @author Administrator
 */
public class TitlebarSearchUI extends FrameLayout implements OnClickListener ,TitleBarInterface {


    private EditText etSearch;
    private Activity mActivity;
    private OnSearchListener mSearchListener;

    public TitlebarSearchUI(@NonNull Context context) {
        this(context, null);
    }

    public TitlebarSearchUI(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitlebarSearchUI(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.ui_titlebar_search, this);

        etSearch = findViewById(R.id.et_search);
        findViewById(R.id.iv_left).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);
        findViewById(R.id.rl_right_iv_container).setOnClickListener(this);
    }

    @Override
    public void setTitle(String title) {

    }

    public void bindActivity(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void setTitleClickListener(TitlebarUI.TitleBarClickListner listener) {

    }

    @Override
    public View selfView() {
        return this;
    }

    public void setSearchListener(OnSearchListener listener) {
        this.mSearchListener = listener;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_left) {
            if (null != mActivity) {
                mActivity.finish();
            }
        } else if (i == R.id.tv_search) {
            if (mSearchListener != null) {
                mSearchListener.onSearch(etSearch.getText().toString().trim());
            }
        }
    }

    public interface OnSearchListener {
        void onSearch(String text);
    }

}
